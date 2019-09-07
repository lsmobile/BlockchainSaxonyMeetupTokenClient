/*	Basic client side code to interact with the Saxony Meetup Token Contract 
/	Takes the Ethereum address of user and the secret, given during a meetup.
/	Outputs a string to the console containing a hash, that is the input of the getToken() function of the smart contract.
/	@author Tim Menapace
*/	
//import web3 library
const Web3 = require('web3');
//import BigNum library
const BN = require('bn.js');

// create an instance of web3 using the HTTP provider.
var web3 = new Web3(new Web3.providers.HttpProvider("http://localhost:8545"));
// Enter your Ethereum public address
var userAddress = "0x0b83cd29f5e3e7a071d8f6e99dbdf28120d921bf"
console.log("User address:" + userAddress);

// The secret for the current secret
var secret = "saxony";
console.log("secret: " + secret);

//Hash of the user's address
var hashUser = web3.utils.soliditySha3(userAddress);
console.log("Hash of the user's address: " + hashUser);

//Encode the current secret
var secretToHex = web3.utils.utf8ToHex(secret);
console.log("secret encoded as Hex: " + secretToHex);
var secretEncoded = web3.eth.abi.encodeParameter('bytes32', secretToHex);
console.log('secret encoded ABI: ' + secretEncoded);

//Create two Big Numbers and XOR with bn.js library
var a = new BN(hashUser, 16);
var b = new BN(secretEncoded, 16);
var addressSecret = '0x' + a.xor(b).toString(16);
//console.log("Address Secret: " + addressSecret);

//Hash the XOR result. This hash is the payload that gets send towards the smart contract.
var result = web3.utils.soliditySha3(addressSecret);
console.log("Final result (hash of address secret): " + result);

