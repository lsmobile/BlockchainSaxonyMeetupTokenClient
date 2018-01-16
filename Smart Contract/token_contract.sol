/*

This Token Contract implements the standard token functionality (https://github.com/ethereum/EIPs/issues/20) as well as the following OPTIONAL extras intended for use by humans.



In other words. This is intended for deployment in something like a Token Factory or Mist wallet, and then used by humans.

Imagine coins, currencies, shares, voting weight, etc.

Machine-based, rapid creation of many tokens would not necessarily need these extra features or will be minted in other manners.



1) Initial Finite Supply (upon creation one specifies how much is minted).

2) In the absence of a token registry: Optional Decimal, Symbol & Name.

3) Optional approveAndCall() functionality to notify a contract if an approval() has occurred.



.*/



import "./StandardToken.sol";



pragma solidity ^0.4.8;



contract BlockchainSaxonyMeetupCoin is StandardToken {



    /* Public variables of the token */



    /*

    NOTE:

    The following variables are OPTIONAL vanities. One does not have to include them.

    They allow one to customise the token contract & in no way influences the core functionality.

    Some wallets/interfaces might not even bother to look at this information.

    */

    string public name;                   //fancy name: eg Simon Bucks

    uint8 public decimals;                //How many decimals to show. ie. There could 1000 base units with 3 decimals. Meaning 0.980 SBX = 980 base units. It's like comparing 1 wei to 1 ether.

    string public symbol;                 //An identifier: eg SBX

    string public version = '0.1';       //human 0.1 standard. Just an arbitrary versioning scheme.

    address public owner;

    mapping (address => bool) public members;

    struct typClaim{                        

        address user;
        bytes32 addressSecret;
    }

    typClaim [] claims;                         // array of struct. To be able to iterate over the users claiming tokens.


    function BlockchainSaxonyMeetupCoin() {

        totalSupply = 0;                        // Update total supply

        name = "BlockchainSaxonyMeetupCoin";                                   // Set the name for display purposes

        decimals = 0;                           // Amount of decimals for display purposes

        symbol = "SAX";                         // Set the symbol for display purposes

        owner = msg.sender;

        members[owner] = true;

    }

    // User calls getToken with his addressSecret
    // Assignment: Address -> addressSecret (Hash (Address ^ Secret _bytes32))
    // addressSecret auf Userseite generieren -> TODO
    // Eine Member Addresse kann mehrfach getToken aufrufen. Evtl in setSecret() lösen. Nur einmal auszahlen! -> TODO 


    function getToken(bytes32 addressSecret) returns (bool success) {

        require (members[msg.sender]);      // check if msg.sender is in members
        
        uint i = claims.length;             // get array length
        claims.length++;                    // increase array length
        claims[i].user = msg.sender;        // write current msg.sender into latest index of claims[].user
        claims[i].addressSecret = addressSecret;    //write addressSecret into latest index of claims[].addressSecret

        return true;
    }

    function setMembers(address _member) returns (bool success) {

        require(msg.sender == owner);       // check if msg.sender is owner

        members[_member] = true;            // add address to members mapping

        return true;

    }

    // Validate Mapping. Check if address + secret = hash
    // This function gets called after every blockchain meetup by the owner.
    // End of function -> Delete array

    function setSecret(bytes32 _secret) {

        require(msg.sender == owner);       // check if msg.sender is owner
        uint256 amount = 5;                 // every user gets *amount* tokens

        for (uint i = 0; i < claims.length; i++){

//Hash of adress alone. Resulting hash bit-by-bit XOR secret and hash again.
            bytes32 hashAddress = keccak256(claims[i].user);
            bytes32 validHash = keccak256(hashAddress ^ _secret);

            if (validHash == claims[i].addressSecret){

                totalSupply += amount;

                balances[claims[i].user] += amount;

            }
            delete claims[i];
        }
        claims.length = claims.length - i;

    }



    /* Approves and then calls the receiving contract */

    function approveAndCall(address _spender, uint256 _value, bytes _extraData) returns (bool success) {

        allowed[msg.sender][_spender] = _value;

        Approval(msg.sender, _spender, _value);



        //call the receiveApproval function on the contract you want to be notified. This crafts the function signature manually so one doesn't have to include a contract in here just for this.

        //receiveApproval(address _from, uint256 _value, address _tokenContract, bytes _extraData)

        //it is assumed when one does this that the call *should* succeed, otherwise one would use vanilla approve instead.

        require(_spender.call(bytes4(bytes32(sha3("receiveApproval(address,uint256,address,bytes)"))), msg.sender, _value, this, _extraData));

        return true;

    }

}
