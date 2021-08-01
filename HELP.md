

var request = JSON.parse('{"name":"benjaminsleek@gmail.com"}');
var response;
$.post({ url: "http://localhost:8080/registration/challenge",
        data: JSON.stringify(request),
        contentType:"application/json; charset=utf-8",
        dataType:"json",
        success: function( data ) {
            response = data;
        }
});


const publicKeyCredentialCreationOptions = {
    challenge: Uint8Array.from(
        response.randomBytes, c => c.charCodeAt(0)),
    rp: {
        name: "localhost",
        id: "localhost",
    },
    user: {
        id: Uint8Array.from(
            "UZSL85T9AFC", c => c.charCodeAt(0)),
        name: "benjaminsleek@gmail.com",
        displayName: "Ben",
    },
    pubKeyCredParams: [{alg: -7, type: "public-key"}],
    authenticatorSelection: {
        authenticatorAttachment: "cross-platform",
    },
    timeout: 60000,
    attestation: "direct"
};


const credential = await navigator.credentials.create({
    publicKey: publicKeyCredentialCreationOptions
});


const utf8Decoder = new TextDecoder('utf-8');
const decodedClientData = utf8Decoder.decode(
    credential.response.clientDataJSON)

// requires a library for CBOR

const decodedAttestationObj = CBOR.decode(
    credential.response.attestationObject);

// convert auth data to byte array

base64ArrayBuffer(decodedAttestationObj.authData)

base64ArrayBuffer(decodedAttestationObj.attStmt.sig)

// use key id, authdata and attStmt.sig in request





/=========================================================================================/

Now onto authentication...

Convert base64 to array buffer


// Call /login/challenge to get id

const credentialId = _base64ToArrayBuffer('');


const publicKeyCredentialRequestOptions = {
    challenge: Uint8Array.from(
        'somethingreallyrandom', c => c.charCodeAt(0)),
    allowCredentials: [{
        id: credentialId,
        type: 'public-key',
        transports: ['usb', 'ble', 'nfc'],
    }],
    timeout: 60000,
}

First step is to get the credential.

const credential = await navigator.credentials.get({
    publicKey: publicKeyCredentialRequestOptions
});

Now lets prepare the request to the server

base64ArrayBuffer(credential.response.authenticatorData)

base64ArrayBuffer(credential.response.signature)

const utf8Decoder = new TextDecoder('utf-8');
const decodedClientData = utf8Decoder.decode(
    credential.response.clientDataJSON) 
