package org.bcsm.tokenclient.business.smartcontracts;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.0.1.
 */
public final class BlockchainSaxonyMeetupCoin extends Contract {
    private static final String BINARY = "606060405260408051908101604052600381527f302e310000000000000000000000000000000000000000000000000000000000602082015260069080516200004d92916020019062000161565b5034156200005a57600080fd5b6000805560408051908101604052601a81527f426c6f636b636861696e5361786f6e794d6565747570436f696e00000000000060208201526003908051620000a792916020019062000161565b506004805460ff1916905560408051908101604052600381527f534158000000000000000000000000000000000000000000000000000000000060208201526005908051620000fb92916020019062000161565b5060088054600160a060020a03191633600160a060020a031617905560098054600181016200012b8382620001e6565b5060009182526020909120018054600160a060020a03191673dd870fa1b7c4700f2bd7f44238821c26f739214817905562000232565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10620001a457805160ff1916838001178555620001d4565b82800160010185558215620001d4579182015b82811115620001d4578251825591602001919060010190620001b7565b50620001e292915062000212565b5090565b8154818355818115116200020d576000838152602090206200020d91810190830162000212565b505050565b6200022f91905b80821115620001e2576000815560010162000219565b90565b610b0680620002426000396000f3006060604052600436106100d75763ffffffff60e060020a60003504166306fdde0381146100dc578063095ea7b314610166578063155bf4e21461019c57806318160ddd146101b257806323b872dd146101d7578063313ce567146101ff57806335a9c82f1461022857806354fd4d50146102405780635daf08ca1461025357806370a08231146102855780638da5cb5b146102a457806395d89b41146102b7578063a9059cbb146102ca578063cae9ca51146102ec578063d1efd30d14610351578063dd62ed3e14610364578063f3796bdc14610389575b600080fd5b34156100e757600080fd5b6100ef6103a8565b60405160208082528190810183818151815260200191508051906020019080838360005b8381101561012b578082015183820152602001610113565b50505050905090810190601f1680156101585780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b341561017157600080fd5b610188600160a060020a0360043516602435610446565b604051901515815260200160405180910390f35b34156101a757600080fd5b6101886004356104b2565b34156101bd57600080fd5b6101c5610562565b60405190815260200160405180910390f35b34156101e257600080fd5b610188600160a060020a0360043581169060243516604435610568565b341561020a57600080fd5b61021261066e565b60405160ff909116815260200160405180910390f35b341561023357600080fd5b61023e600435610677565b005b341561024b57600080fd5b6100ef610697565b341561025e57600080fd5b610269600435610702565b604051600160a060020a03909116815260200160405180910390f35b341561029057600080fd5b6101c5600160a060020a036004351661072a565b34156102af57600080fd5b610269610745565b34156102c257600080fd5b6100ef610754565b34156102d557600080fd5b610188600160a060020a03600435166024356107bf565b34156102f757600080fd5b61018860048035600160a060020a03169060248035919060649060443590810190830135806020601f8201819004810201604051908101604052818152929190602084018383808284375094965061085395505050505050565b341561035c57600080fd5b6101c56109f3565b341561036f57600080fd5b6101c5600160a060020a03600435811690602435166109f9565b341561039457600080fd5b610188600160a060020a0360043516610a24565b60038054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561043e5780601f106104135761010080835404028352916020019161043e565b820191906000526020600020905b81548152906001019060200180831161042157829003601f168201915b505050505081565b600160a060020a03338116600081815260026020908152604080832094871680845294909152808220859055909291907f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b9259085905190815260200160405180910390a350600192915050565b60075460009081908190819085141561055557600092505b6009548310156105175760098054849081106104e257fe5b60009182526020909120015433600160a060020a039081169116141561050c576009549250600191505b6001909201916104ca565b81151561052357600080fd5b506000805460059081018255600160a060020a033316825260016020819052604090922080548201905590935061055a565b600093505b505050919050565b60005481565b600160a060020a0380841660008181526002602090815260408083203390951683529381528382205492825260019052918220548390108015906105ac5750828110155b15156105b757600080fd5b600160a060020a038085166000908152600160205260408082208054870190559187168152208054849003905560001981101561061c57600160a060020a03808616600090815260026020908152604080832033909416835292905220805484900390555b83600160a060020a031685600160a060020a03167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef8560405190815260200160405180910390a3506001949350505050565b60045460ff1681565b60085433600160a060020a0390811691161461069257600080fd5b600755565b60068054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561043e5780601f106104135761010080835404028352916020019161043e565b600980548290811061071057fe5b600091825260209091200154600160a060020a0316905081565b600160a060020a031660009081526001602052604090205490565b600854600160a060020a031681565b60058054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561043e5780601f106104135761010080835404028352916020019161043e565b600160a060020a033316600090815260016020526040812054829010156107e557600080fd5b600160a060020a033381166000818152600160205260408082208054879003905592861680825290839020805486019055917fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef9085905190815260200160405180910390a350600192915050565b600160a060020a03338116600081815260026020908152604080832094881680845294909152808220869055909291907f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b9259086905190815260200160405180910390a383600160a060020a03166040517f72656365697665417070726f76616c28616464726573732c75696e743235362c81527f616464726573732c6279746573290000000000000000000000000000000000006020820152602e01604051809103902060e060020a9004338530866040518563ffffffff1660e060020a0281526004018085600160a060020a0316600160a060020a0316815260200184815260200183600160a060020a0316600160a060020a03168152602001828051906020019080838360005b8381101561099457808201518382015260200161097c565b50505050905090810190601f1680156109c15780820380516001836020036101000a031916815260200191505b5094505050505060006040518083038160008761646e5a03f19250505015156109e957600080fd5b5060019392505050565b60075481565b600160a060020a03918216600090815260026020908152604080832093909416825291909152205490565b60085460009033600160a060020a03908116911614610a4257600080fd5b6009805460018101610a548382610a90565b5060009182526020909120018054600160a060020a03841673ffffffffffffffffffffffffffffffffffffffff19909116179055506001919050565b815481835581811511610ab457600083815260209020610ab4918101908301610ab9565b505050565b610ad791905b80821115610ad35760008155600101610abf565b5090565b905600a165627a7a72305820f8ce670ee21689d090759b4f7bc6a572affcafbf376d17eb8d980283042cdc330029";

    private BlockchainSaxonyMeetupCoin(final String contractAddress, final Web3j web3j, final Credentials credentials, final BigInteger gasPrice, final BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private BlockchainSaxonyMeetupCoin(final String contractAddress, final Web3j web3j, final TransactionManager transactionManager, final BigInteger gasPrice, final BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<TransferEventResponse> getTransferEvents(final TransactionReceipt transactionReceipt) {
        final Event event = new Event("Transfer",
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        final List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        final ArrayList<TransferEventResponse> responses = new ArrayList<>(valueList.size());
        for (final EventValues eventValues : valueList) {
            final TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse._from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse._value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<TransferEventResponse> transferEventObservable(final DefaultBlockParameter startBlock, final DefaultBlockParameter endBlock) {
        final Event event = new Event("Transfer",
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        final EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return this.web3j.ethLogObservable(filter).map(new Func1<Log, TransferEventResponse>() {
            @Override
            public TransferEventResponse call(final Log log) {
                final EventValues eventValues = extractEventParameters(event, log);
                final TransferEventResponse typedResponse = new TransferEventResponse();
                typedResponse._from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse._to = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse._value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public List<ApprovalEventResponse> getApprovalEvents(final TransactionReceipt transactionReceipt) {
        final Event event = new Event("Approval",
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        final List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        final ArrayList<ApprovalEventResponse> responses = new ArrayList<>(valueList.size());
        for (final EventValues eventValues : valueList) {
            final ApprovalEventResponse typedResponse = new ApprovalEventResponse();
            typedResponse._owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._spender = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse._value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ApprovalEventResponse> approvalEventObservable(final DefaultBlockParameter startBlock, final DefaultBlockParameter endBlock) {
        final Event event = new Event("Approval",
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        final EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return this.web3j.ethLogObservable(filter).map(new Func1<Log, ApprovalEventResponse>() {
            @Override
            public ApprovalEventResponse call(final Log log) {
                final EventValues eventValues = extractEventParameters(event, log);
                final ApprovalEventResponse typedResponse = new ApprovalEventResponse();
                typedResponse._owner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse._spender = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse._value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public RemoteCall<String> name() {
        final Function function = new Function("name",
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> approve(final String _spender, final BigInteger _value) {
        final Function function = new Function(
                "approve",
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_spender),
                new org.web3j.abi.datatypes.generated.Uint256(_value)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> getToken(final byte[] _secret) {
        final Function function = new Function(
                "getToken",
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_secret)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> totalSupply() {
        final Function function = new Function("totalSupply",
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> transferFrom(final String _from, final String _to, final BigInteger _value) {
        final Function function = new Function(
                "transferFrom",
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_from),
                new org.web3j.abi.datatypes.Address(_to),
                new org.web3j.abi.datatypes.generated.Uint256(_value)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> decimals() {
        final Function function = new Function("decimals",
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> setSecret(final byte[] _secret) {
        final Function function = new Function(
                "setSecret",
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_secret)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> version() {
        final Function function = new Function("version",
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> members(final BigInteger param0) {
        final Function function = new Function("members",
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> balanceOf(final String _owner) {
        final Function function = new Function("balanceOf",
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_owner)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> owner() {
        final Function function = new Function("owner",
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> symbol() {
        final Function function = new Function("symbol",
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> transfer(final String _to, final BigInteger _value) {
        final Function function = new Function(
                "transfer",
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_to),
                new org.web3j.abi.datatypes.generated.Uint256(_value)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> approveAndCall(final String _spender, final BigInteger _value, final byte[] _extraData) {
        final Function function = new Function(
                "approveAndCall",
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_spender),
                new org.web3j.abi.datatypes.generated.Uint256(_value),
                new org.web3j.abi.datatypes.DynamicBytes(_extraData)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<byte[]> secret() {
        final Function function = new Function("secret",
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<BigInteger> allowance(final String _owner, final String _spender) {
        final Function function = new Function("allowance",
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_owner),
                new org.web3j.abi.datatypes.Address(_spender)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> setMembers(final String _member) {
        final Function function = new Function(
                "setMembers",
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_member)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static RemoteCall<BlockchainSaxonyMeetupCoin> deploy(final Web3j web3j, final Credentials credentials, final BigInteger gasPrice, final BigInteger gasLimit) {
        return deployRemoteCall(BlockchainSaxonyMeetupCoin.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<BlockchainSaxonyMeetupCoin> deploy(final Web3j web3j, final TransactionManager transactionManager, final BigInteger gasPrice, final BigInteger gasLimit) {
        return deployRemoteCall(BlockchainSaxonyMeetupCoin.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static BlockchainSaxonyMeetupCoin load(final String contractAddress, final Web3j web3j, final Credentials credentials, final BigInteger gasPrice, final BigInteger gasLimit) {
        return new BlockchainSaxonyMeetupCoin(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static BlockchainSaxonyMeetupCoin load(final String contractAddress, final Web3j web3j, final TransactionManager transactionManager, final BigInteger gasPrice, final BigInteger gasLimit) {
        return new BlockchainSaxonyMeetupCoin(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class TransferEventResponse {
        public String _from;

        public String _to;

        public BigInteger _value;
    }

    public static class ApprovalEventResponse {
        public String _owner;

        public String _spender;

        public BigInteger _value;
    }
}
