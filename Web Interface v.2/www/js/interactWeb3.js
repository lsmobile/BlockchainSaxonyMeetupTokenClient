
window.addEventListener('load', function() {

  // Checking if Web3 has been injected by the browser (Mist/MetaMask)
  if (typeof web3 !== 'undefined') {
    // Use Mist/MetaMask's provider
    web3js = new Web3(web3.currentProvider);
    console.log('Injected web3 dectected. Creating web3 object and starting app :)');
  } else {
    console.log('No web3? You should consider trying MetaMask!')
    // fallback - use your fallback strategy (local node / hosted node + in-dapp id mgmt / fail)
    throw document.getElementById('warning').innerHTML= "Can't locate MetaMask plugin. Please install MetaMask first.";
  }
  loggedIn();
  checkNetwork();

});

// Check if user is logged into MetaMask
function loggedIn(){
web3.eth.getAccounts(function(err, accounts){
    if (err != null) console.error("An error occurred: " + err);
    else if (accounts.length == 0) {
      console.log("User is not logged in to MetaMask");
      document.getElementById('warning').innerHTML= "Please log into MetaMask";
    }
    else console.log("User is logged in to MetaMask");
})};

// Check if user is on the correct network
function checkNetwork(){
web3.version.getNetwork((err, netId) => {
  switch (netId) {
    case "1":
      console.log('This is mainnet')
      document.getElementById('warning').innerHTML= "You are on mainnet. Please change to Rinkeby test network.";
      break
    case "2":
      console.log('This is the deprecated Morden test network.')
      document.getElementById('warning').innerHTML= "You are on Morden network. Please change to Rinkeby test network.";
      break
    case "3":
      console.log('This is the ropsten test network.')
      document.getElementById('warning').innerHTML= "You are on Ropsten network. Please change to Rinkeby test network.";
      break
    case "4":
      console.log('This is the Rinkeby test network.')
      break
    case "42":
      console.log('This is the Kovan test network.')
      document.getElementById('warning').innerHTML= "You are on Kovan test network. Please change to Rinkeby test network.";
      break
    default:
      console.log('This is an unknown network.')
      document.getElementById('warning').innerHTML= "Please change to Rinkeby test network.";
  }
}) };


//Prepares the secret for the transaction
function encodeSecret(){

loggedIn();
checkNetwork();

var contractAddress = "0x23a610a8aa3582ef0ad45c60a7c66162b5d5b6c8";

// Get the user address from Metamask (injected web3) 
var userAddress = web3.eth.defaultAccount;
console.log(userAddress);

//Hash of the user's address
var hashUser = web3.sha3(userAddress, {encoding: 'hex'});
//console.log("Hash of the user's address: " + hashUser);

// Get the secret from user input
var secret = document.getElementById('secret').value;
if (secret.length === 0) throw document.getElementById('warning').innerHTML= "Secret can't be empty.";
console.log(secret);

// Encode the secret
var secretToHex = web3.toHex(secret);
console.log("secret encoded as Hex: " + secretToHex);

// Padding Funktion selber basteln, da web3 1.0 nicht mit MetaMask geht und die web3 0.2 fromAscii funktion nicht funkctioniert
var l = secretToHex.length;
// 66 hex characters in bytes32
if (l > 66) {
  throw alert("Secret too long. Maximum 32 characters.");
}
var padding = ""
for (l; l < 66; l++){
  padding = padding.concat("0");
}
secretToHex = secretToHex.concat(padding);
console.log("secretToHex with padding: " + secretToHex);

//Create two Big Numbers and XOR with bn.js library
var a = new BN(hashUser, 16);
console.log("BigNumber hashUser: " + a);

var b = new BN(secretToHex, 16);
console.log("BigNumber secretToHex: " + b);

var addressSecret = '0x' + a.xor(b).toString(16);
console.log("Address Secret: " + addressSecret);

//Hash the XOR result. This hash is the payload that gets send towards the smart contract.
var payload = web3.sha3(addressSecret, {encoding: 'hex'});
console.log("Final result (hash of address secret): " + payload);

// call sendTransaction with the final payload
sendTx(payload, contractAddress);

};



// Sends the formatted secret from the user to the contract 
function sendTx(payload, contractAddress){

// Application Binary Interface of the Contract. Retrieved from remix
var abi = [
  {
    "constant": true,
    "inputs": [],
    "name": "version",
    "outputs": [
      {
        "name": "",
        "type": "string"
      }
    ],
    "payable": false,
    "stateMutability": "view",
    "type": "function"
  },
  {
    "constant": true,
    "inputs": [
      {
        "name": "_owner",
        "type": "address"
      },
      {
        "name": "_spender",
        "type": "address"
      }
    ],
    "name": "allowance",
    "outputs": [
      {
        "name": "",
        "type": "uint256"
      }
    ],
    "payable": false,
    "stateMutability": "view",
    "type": "function"
  },
  {
    "constant": true,
    "inputs": [],
    "name": "totalSupply",
    "outputs": [
      {
        "name": "",
        "type": "uint256"
      }
    ],
    "payable": false,
    "stateMutability": "view",
    "type": "function"
  },
  {
    "constant": true,
    "inputs": [],
    "name": "symbol",
    "outputs": [
      {
        "name": "",
        "type": "string"
      }
    ],
    "payable": false,
    "stateMutability": "view",
    "type": "function"
  },
  {
    "constant": true,
    "inputs": [
      {
        "name": "_owner",
        "type": "address"
      }
    ],
    "name": "balanceOf",
    "outputs": [
      {
        "name": "balance",
        "type": "uint256"
      }
    ],
    "payable": false,
    "stateMutability": "view",
    "type": "function"
  },
  {
    "constant": true,
    "inputs": [],
    "name": "decimals",
    "outputs": [
      {
        "name": "",
        "type": "uint8"
      }
    ],
    "payable": false,
    "stateMutability": "view",
    "type": "function"
  },
  {
    "constant": true,
    "inputs": [],
    "name": "owner",
    "outputs": [
      {
        "name": "",
        "type": "address"
      }
    ],
    "payable": false,
    "stateMutability": "view",
    "type": "function"
  },
  {
    "constant": true,
    "inputs": [],
    "name": "name",
    "outputs": [
      {
        "name": "",
        "type": "string"
      }
    ],
    "payable": false,
    "stateMutability": "view",
    "type": "function"
  },
  {
    "constant": true,
    "inputs": [
      {
        "name": "",
        "type": "address"
      }
    ],
    "name": "members",
    "outputs": [
      {
        "name": "",
        "type": "bool"
      }
    ],
    "payable": false,
    "stateMutability": "view",
    "type": "function"
  },
  {
    "constant": false,
    "inputs": [
      {
        "name": "_spender",
        "type": "address"
      },
      {
        "name": "_addedValue",
        "type": "uint256"
      }
    ],
    "name": "increaseApproval",
    "outputs": [
      {
        "name": "",
        "type": "bool"
      }
    ],
    "payable": false,
    "stateMutability": "nonpayable",
    "type": "function"
  },
  {
    "inputs": [],
    "payable": false,
    "stateMutability": "nonpayable",
    "type": "constructor"
  },
  {
    "anonymous": false,
    "inputs": [
      {
        "indexed": true,
        "name": "owner",
        "type": "address"
      },
      {
        "indexed": true,
        "name": "spender",
        "type": "address"
      },
      {
        "indexed": false,
        "name": "value",
        "type": "uint256"
      }
    ],
    "name": "Approval",
    "type": "event"
  },
  {
    "constant": false,
    "inputs": [
      {
        "name": "_from",
        "type": "address"
      },
      {
        "name": "_to",
        "type": "address"
      },
      {
        "name": "_value",
        "type": "uint256"
      }
    ],
    "name": "transferFrom",
    "outputs": [
      {
        "name": "",
        "type": "bool"
      }
    ],
    "payable": false,
    "stateMutability": "nonpayable",
    "type": "function"
  },
  {
    "anonymous": false,
    "inputs": [
      {
        "indexed": true,
        "name": "from",
        "type": "address"
      },
      {
        "indexed": true,
        "name": "to",
        "type": "address"
      },
      {
        "indexed": false,
        "name": "value",
        "type": "uint256"
      }
    ],
    "name": "Transfer",
    "type": "event"
  },
  {
    "constant": false,
    "inputs": [
      {
        "name": "_to",
        "type": "address"
      },
      {
        "name": "_value",
        "type": "uint256"
      }
    ],
    "name": "transfer",
    "outputs": [
      {
        "name": "",
        "type": "bool"
      }
    ],
    "payable": false,
    "stateMutability": "nonpayable",
    "type": "function"
  },
  {
    "constant": false,
    "inputs": [
      {
        "name": "_spender",
        "type": "address"
      },
      {
        "name": "_value",
        "type": "uint256"
      }
    ],
    "name": "approve",
    "outputs": [
      {
        "name": "",
        "type": "bool"
      }
    ],
    "payable": false,
    "stateMutability": "nonpayable",
    "type": "function"
  },
  {
    "constant": false,
    "inputs": [
      {
        "name": "_spender",
        "type": "address"
      },
      {
        "name": "_value",
        "type": "uint256"
      },
      {
        "name": "_extraData",
        "type": "bytes"
      }
    ],
    "name": "approveAndCall",
    "outputs": [
      {
        "name": "success",
        "type": "bool"
      }
    ],
    "payable": false,
    "stateMutability": "nonpayable",
    "type": "function"
  },
  {
    "constant": false,
    "inputs": [
      {
        "name": "addressSecret",
        "type": "bytes32"
      }
    ],
    "name": "getToken",
    "outputs": [
      {
        "name": "success",
        "type": "bool"
      }
    ],
    "payable": false,
    "stateMutability": "nonpayable",
    "type": "function"
  },
  {
    "constant": false,
    "inputs": [
      {
        "name": "_secret",
        "type": "bytes32"
      }
    ],
    "name": "setSecret",
    "outputs": [],
    "payable": false,
    "stateMutability": "nonpayable",
    "type": "function"
  },
  {
    "constant": false,
    "inputs": [
      {
        "name": "_member",
        "type": "address"
      }
    ],
    "name": "setMembers",
    "outputs": [
      {
        "name": "success",
        "type": "bool"
      }
    ],
    "payable": false,
    "stateMutability": "nonpayable",
    "type": "function"
  },
  {
    "constant": false,
    "inputs": [
      {
        "name": "_spender",
        "type": "address"
      },
      {
        "name": "_subtractedValue",
        "type": "uint256"
      }
    ],
    "name": "decreaseApproval",
    "outputs": [
      {
        "name": "",
        "type": "bool"
      }
    ],
    "payable": false,
    "stateMutability": "nonpayable",
    "type": "function"
  }
]

// creation of contract object
var MyContract = web3.eth.contract(abi);

console.log("contractAddress: " + contractAddress);
console.log("payload: " + payload);

// initiate contract for specified address
var myContractInstance = MyContract.at(contractAddress);

// get the formated data of the function call (method name + payload)
var transactionData = myContractInstance.getToken.getData(payload);
console.log("transactionData: " + transactionData);

// send the transaction towards the contract
web3.eth.sendTransaction({to: contractAddress ,data: transactionData}, function(err, transactionHash) {
  if (!err)
    console.log("Transaction ID:" + transactionHash);
    var link = "<a target='_blank' href = 'https://rinkeby.etherscan.io/tx/" + transactionHash + "'> " + transactionHash + " </a>";
    document.getElementById('output').innerHTML = "Your transaction ID: " + link;
});

};








