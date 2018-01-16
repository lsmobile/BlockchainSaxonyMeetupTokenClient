package org.bcsm.tokenclient.business;

import java.io.File;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.bcsm.tokenclient.business.smartcontracts.BlockchainSaxonyMeetupCoin;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.infura.InfuraHttpService;
import org.web3j.tx.Contract;

import rx.Subscription;

public class EthereumConnection implements ICommon {

	public static final String DEFAULT_URL = "https://ropsten.infura.io/sS899But8ce9sP7plpOl";// "http://localhost:8545/"
	public static final String INFURA_SUBSTRING = "infura.io";

	protected String nodeUrl;
	protected Web3j web3;
	protected Web3ClientVersion web3ClientVersion;

	protected String contractAddr;
	protected Subscription eventTransferSubscription;
	protected Subscription eventApprovalSubscription;
	protected BlockchainSaxonyMeetupCoin contract;
	protected String contractName;
	protected String contractVersion;
	protected String owner;
	protected byte[] secret;

	protected String tokenSymbol;
	protected BigInteger totalSupply;
	protected BigInteger decimals;

	protected File walletfile;
	protected Credentials credentials;
	protected String address;
	protected BigInteger balance = BigInteger.ZERO;

	public boolean logTransactionReceipt = true;

	public boolean connect(String nodeUrl) throws InterruptedException, ExecutionException {
		this.nodeUrl = nodeUrl;
		if (nodeUrl == null || nodeUrl.length() == 0) {
			nodeUrl = DEFAULT_URL;
		}
		log.info("Connecting to '" + nodeUrl + "'...");
		if (nodeUrl.contains(INFURA_SUBSTRING)) {
			this.web3 = Web3j.build(new InfuraHttpService(nodeUrl));
		} else {
			this.web3 = /* Parity TODO */Web3j.build(new HttpService(nodeUrl));
		}
		this.web3ClientVersion = this.web3.web3ClientVersion().sendAsync().get();
		if (this.web3ClientVersion != null) {
			log.info("Connected to the node '" + nodeUrl + "' . Client version from the node: "
					+ this.web3ClientVersion.getWeb3ClientVersion());
		}
		return isConnected();
	}

	public void disconnect() {
		unsubscribeEvents();
		this.web3 = null;
		this.web3ClientVersion = null;
		log.info("Disconnected from the node  '" + this.nodeUrl + "'.");
	}

	public boolean isConnected() {
		return this.web3 != null && this.web3ClientVersion != null;
	}

	public void setWalletfile(final File walletfile) {
		this.walletfile = walletfile;
		log.info("Wallet " + walletfile.getName() + " is uploaded");
	}

	public boolean canLoggin() {
		return isConnected() && this.walletfile != null;
		// TODO && blockchainConnector.passwordField.getValue().length() > 0
	}

	public boolean login(final String password) throws Exception {
		if (conn.web3ClientVersion != null && this.walletfile != null && password != null) {
			this.credentials = WalletUtils.loadCredentials(password, this.walletfile);
			this.address = this.credentials.getAddress();
			log.info("The user with the wallet '" + this.walletfile.getName() + "' is logged in.");
			log.info("Your address: " + this.address);
			return isLogged();
		}
		return false;
	}

	public boolean isLogged() {
		return isConnected() && this.credentials != null;
	}

	public boolean loadContract(final String contractAddr) throws Exception {
		if (contractAddr != null && this.web3 != null && this.credentials != null) {
			this.contractAddr = contractAddr;
			log.info("The smart contract '" + contractAddr + "' is loading...");
			this.contract = BlockchainSaxonyMeetupCoin.load(contractAddr, this.web3, this.credentials,
					Contract.GAS_PRICE, Contract.GAS_LIMIT);
			log.info("The smart contract '" + contractAddr + "' is loaded. The public contract fields:");
			log.info("Name: " + (this.contractName = this.contract.name().send()));
			log.info("Version: " + (this.contractVersion = this.contract.version().send()));
			log.info("Symbol: " + (this.tokenSymbol = this.contract.symbol().send()));
			log.info("Total supply: " + (this.totalSupply = this.contract.totalSupply().send()));
			log.info("Decimals: " + (this.decimals = this.contract.decimals().send()));
			log.info("Owner: " + (this.owner = this.contract.owner().send()));
			log.info("Your balance: " + (this.balance = this.contract.balanceOf(this.address).send()));
			log.info("Balance of the owner: " + this.contract.balanceOf(this.owner).send());
			this.secret = this.contract.secret().send();
			return isLogged();
		}
		return false;
	}

	public boolean isContractLoaded() {
		return isLogged() && this.contract != null;
	}
	
	public void subscribeEvents(final String contractAddr) {
		if (this.nodeUrl != null && this.nodeUrl.contains(INFURA_SUBSTRING)) {
			log.error("!!! Events are not supported on Infura !!!");
			return;
		}
		if (this.eventTransferSubscription == null)
		{
			final org.web3j.abi.datatypes.Event eventTransfer = new org.web3j.abi.datatypes.Event(
					"Transfer",
					Arrays.asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}),
					Arrays.asList(new TypeReference<Uint256>() {}));
			this.eventTransferSubscription = this.web3.ethLogObservable(new EthFilter(DefaultBlockParameterName.EARLIEST, DefaultBlockParameterName.LATEST,
					contractAddr).addSingleTopic(EventEncoder.encode(eventTransfer))).subscribe(log_ -> {
						final List<Type> results = FunctionReturnDecoder.decode(log_.getData(), eventTransfer.getNonIndexedParameters());
						log.info("Event 'Transfer': Time=" + (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date())
								+ ", _from=" + log_.getTopics().get(1)
								+ ", _to=" + log_.getTopics().get(2)
								+ ", _value=" + ((Type<?>) results.get(0)).getValue());
			});
			log.info("Event 'Transfer' is subscribed");
		}
		if (this.eventApprovalSubscription == null)
		{
			final org.web3j.abi.datatypes.Event eventApproval = new org.web3j.abi.datatypes.Event(
					"Approval",
					Arrays.asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}),
					Arrays.asList(new TypeReference<Uint256>() {}));
			this.eventApprovalSubscription = this.web3.ethLogObservable(new EthFilter(DefaultBlockParameterName.EARLIEST, DefaultBlockParameterName.LATEST,
					contractAddr).addSingleTopic(EventEncoder.encode(eventApproval))).subscribe(log_ -> {
						final List<Type> results = FunctionReturnDecoder.decode(log_.getData(), eventApproval.getNonIndexedParameters());
						log.info("Event 'Approval': Time=" + (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date())
								+ ", _owner=" + log_.getTopics().get(1)
								+ ", _spender=" + log_.getTopics().get(2)
								+ ", _value=" + ((Type<?>) results.get(0)).getValue());
			});
			log.info("Event 'Approval' is subscribed");
		}
	}

	public void unsubscribeEvents() {
		if (this.eventTransferSubscription != null)
		{
			if (!this.eventTransferSubscription.isUnsubscribed()) {
				this.eventTransferSubscription.unsubscribe();
				log.info("Event 'Transfer' is unsubscribed");
			}
			this.eventTransferSubscription = null;
		}
		if (this.eventApprovalSubscription != null)
		{
			if (!this.eventApprovalSubscription.isUnsubscribed()) {
				this.eventApprovalSubscription.unsubscribe();
				log.info("Event 'Approval' is unsubscribed");
			}
			this.eventApprovalSubscription = null;
		}
	}
	
	public boolean isEventSubscribed() {
		return this.eventTransferSubscription != null && !this.eventTransferSubscription.isUnsubscribed()
				|| this.eventApprovalSubscription != null && !this.eventApprovalSubscription.isUnsubscribed() ;
	}

	public boolean isOwner() {
		return isContractLoaded() && this.owner != null && this.owner.equals(this.address);
	}

	public BigInteger getBalance() {
		return this.balance;
	}

	public void getTokens() throws Exception {
		if (isContractLoaded()) {
			final BigInteger oldBalance = this.balance;
			log.info("Receiving Tokens...");
			final TransactionReceipt receipt = this.contract.getToken(this.secret).send();
			logTransactionReceipt(receipt);
			this.balance = this.contract.balanceOf(this.credentials.getAddress()).send();
			log.info("You have recieved " + this.balance.subtract(oldBalance) + " Tokens");
			log.info("Your new balance: " + this.balance);
		}
	}
	
	public void transferTokens(final String from, final String to, final BigInteger value) throws Exception {
		if (isContractLoaded() && to != null && !to.isEmpty() && value != null) {
			log.info("Transfer Tokens...");
			TransactionReceipt receipt;
			if (from == null || from.isEmpty()) {
				receipt = this.contract.transfer(to, value).send();
			}
			else {
				receipt = this.contract.transferFrom(from, to, value).send();
			}
			logTransactionReceipt(receipt);
			this.balance = this.contract.balanceOf(this.credentials.getAddress()).send();
			log.info("You sent " + value + " Tokens");
			log.info("Your new balance: " + this.balance);
		}
	}

	public void setSecret(final byte[] secret) throws Exception {
		if (isContractLoaded() && secret != null) {
			final TransactionReceipt receipt = this.contract.setSecret(secret).send();
			logTransactionReceipt(receipt);
			this.secret = this.contract.secret().send();
		}
	}

	public void addMember(final String member) throws Exception {
		if (isContractLoaded() && member != null) {
			final TransactionReceipt receipt = this.contract.setMembers(member).send();
			logTransactionReceipt(receipt);
		}
	}

	protected void logTransactionReceipt(final TransactionReceipt receipt) {
		if (this.logTransactionReceipt) {
			log.info("BlockHash: " + receipt.getBlockHash());
			log.info("BlockNumberRaw: " + receipt.getBlockNumberRaw());
			log.info("TransactionHash: " + receipt.getTransactionHash());
			log.info("TransactionIndexRaw: " + receipt.getTransactionIndexRaw());
			log.info("TransactionIndex: " + receipt.getTransactionIndex());
		}
	}
}
