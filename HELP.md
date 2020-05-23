

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



// below is incorrect for a likely more accurate version to conver to base64 try this
https://gist.github.com/jonleighton/958841


// Sample Request

{
   "id":"fIDwcHpaZ3lgWT3vjng1IOhiG_5jw9vxIfUyI5nEHbSaoW2lA2MUiFmdm2z1An0x9GojfsSlSqjfcNgk0NGrIw",
   "type":"public-key",
   "authenticatorAttestationResponse":{
      "attestation":{
         "format":"fido-u2f",
         "authData":"NzMsMTUwLDEzLDIyOSwxMzYsMTQsMTQwLDEwNCwxMTYsNTIsMjMsMTUsMTAwLDExOCw5Niw5MSwxNDMsMjI4LDE3NCwxODUsMTYyLDEzNCw1MCwxOTksMTUzLDkyLDI0MywxODYsMTMxLDI5LDE1MSw5OSw2NSwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCw2NCwxMjQsMTI4LDI0MCwxMTIsMTIyLDkwLDEwMywxMjEsOTYsODksNjEsMjM5LDE0MiwxMjAsNTMsMzIsMjMyLDk4LDI3LDI1NCw5OSwxOTUsMjE5LDI0MSwzMywyNDUsNTAsMzUsMTUzLDE5NiwyOSwxODAsMTU0LDE2MSwxMDksMTY1LDMsOTksMjAsMTM2LDg5LDE1NywxNTUsMTA4LDI0NSwyLDEyNSw0OSwyNDQsMTA2LDM1LDEyNiwxOTYsMTY1LDc0LDE2OCwyMjMsMTEyLDIxNiwzNiwyMDgsMjA5LDE3MSwzNSwxNjUsMSwyLDMsMzgsMzIsMSwzMyw4OCwzMiw2MCw2Nyw2LDc3LDAsMzYsMTEwLDkyLDYwLDk2LDc0LDIxNSw4MywxOTcsMzUsMTc2LDYsMTUxLDEyMiw2MCwyMjgsMTQsMjIsMjQyLDE4Niw2OCw2NywyMjksMjIwLDMzLDM5LDIwOCwzNCw4OCwzMiwxMjAsMjEzLDIyLDc1LDg3LDMxLDI1MywxMDIsMTgxLDE5LDE0NiwxNDUsMjE4LDQzLDYxLDI3LDQ2LDIzMiwyNDcsMTAxLDM1LDIxMiwxMzEsMjUsMjMwLDE0NCwxMTQsMTksMjA1LDE2MSw2OSwyMzk",
         "attestationStatement":{
            "signature":"NDgsNjksMiwzMywwLDEzMiw2LDI0LDE3MywzOCwxNjksODEsMjYsMTA0LDE2MiwxMTEsMjM2LDE0LDIwLDEwNiwzMSwxNDQsNzUsMTEyLDExMywxNTEsMTgxLDE1LDE2NiwyMywxMjksMTcwLDIxNSwxMjcsMTg2LDIzMywyNDksMiwzMiwyNyw3NywxMjQsMTY3LDg5LDEyMSwyMTcsMTE0LDEzOSwxNTIsMTM2LDE4MywyMywxMzksMjMsMTQ1LDE4MSwyNDksODcsMTQ2LDExMywxODEsMTY2LDQxLDU1LDE3NCwyMzksMTk5LDI1LDU0LDEyOCwxMw",
            "certificate":"NDgsMTMwLDIsNzksNDgsMTMwLDEsNTUsMTYwLDMsMiwxLDIsMiw0LDYwLDEwNCw0MSw3Nyw0OCwxMyw2LDksNDIsMTM0LDcyLDEzNCwyNDcsMTMsMSwxLDExLDUsMCw0OCw0Niw0OSw0NCw0OCw0Miw2LDMsODUsNCwzLDE5LDM1LDg5LDExNyw5OCwxMDUsOTksMTExLDMyLDg1LDUwLDcwLDMyLDgyLDExMSwxMTEsMTE2LDMyLDY3LDY1LDMyLDgzLDEwMSwxMTQsMTA1LDk3LDEwOCwzMiw1Miw1Myw1NSw1MCw0OCw0OCw1NCw1MSw0OSw0OCwzMiwyMywxMyw0OSw1Miw0OCw1Niw0OCw0OSw0OCw0OCw0OCw0OCw0OCw0OCw5MCwyNCwxNSw1MCw0OCw1Myw0OCw0OCw1Nyw0OCw1Miw0OCw0OCw0OCw0OCw0OCw0OCw5MCw0OCw0OSw0OSw0Nyw0OCw0NSw2LDMsODUsNCwzLDEyLDM4LDg5LDExNyw5OCwxMDUsOTksMTExLDMyLDg1LDUwLDcwLDMyLDY5LDY5LDMyLDgzLDEwMSwxMTQsMTA1LDk3LDEwOCwzMiw1MCw1MSw1Nyw1MCw1Myw1NSw1MSw1Miw1Niw0OSw0OSw0OSw0OSw1NSw1Nyw0OCw0OSw0OCw4OSw0OCwxOSw2LDcsNDIsMTM0LDcyLDIwNiw2MSwyLDEsNiw4LDQyLDEzNCw3MiwyMDYsNjEsMywxLDcsMyw2NiwwLDQsMTg5LDIyMywxMDMsMTQ3LDIxOSwxMTksMTQ4LDE5NSw4MCw0OSwxMTMsMjM3LDQ0LDc3LDY5LDc0LDIxNywxMTUsMTAyLDExNywyNiw3MiwxODYsMTY1LDIxNywyNDksMTgxLDEwNiw1OCwzMiwxMjksMTIxLDEwNyw3OCwyMjMsNDMsMjA1LDEyMywxNDYsMTIyLDIwLDE0NywxNiwyNTEsMTk2LDEwNCw3MSwxMTEsMjUyLDg3LDE0NiwxNjksMTI1LDE4Miw0NywxNjAsNDAsMTA3LDE5OCwxNjMsMzIsMiwxNCw5OSwxNjMsNTksNDgsNTcsNDgsMzQsNiw5LDQzLDYsMSw0LDEsMTMwLDE5NiwxMCwyLDQsMjEsNDksNDYsNTEsNDYsNTQsNDYsNDksNDYsNTIsNDYsNDksNDYsNTIsNDksNTIsNTYsNTAsNDYsNDksNDYsNTMsNDgsMTksNiwxMSw0Myw2LDEsNCwxLDEzMCwyMjksMjgsMiwxLDEsNCw0LDMsMiw1LDMyLDQ4LDEzLDYsOSw0MiwxMzQsNzIsMTM0LDI0NywxMywxLDEsMTEsNSwwLDMsMTMwLDEsMSwwLDE3MCwxOTIsMTMsODEsOSwxMjYsMjM2LDIxLDE2NCwxMzQsMTc5LDEyMSwxODgsMTk2LDEzMSw2NCwyNDgsMTAzLDIxMSwxNTEsNDYsMjA2LDEwNiwxMDMsMjUxLDE2NCwxMTIsMjI3LDk2LDE5OCw2OCw4OSwyNDAsMTczLDU2LDY2LDIxMSwyNTUsOCwzMCwxMTMsMjM0LDEzNyw4MywxNTEsMjcsMzUsMjQ5LDM3LDE5MiwxNzMsMCwxOTQsNzEsNDksMjQsODIsNiwyNDAsMTc5LDE2OSwyNSw0MSwyNTEsMTQ1LDE4MywxMDIsMTgxLDYzLDIyNSw1MSw4Miw0MiwxMzEsODgsMTg0LDIwOSwzMiwxODAsMTcxLDI1MSwxMjcsMjMyLDIzOSwzNiwxMjMsMTAwLDI1NSwxOTgsMTY2LDMzLDc0LDE5MywyMTcsMTkwLDExNSw1NCwyNTMsMjI0LDI0MywxNjgsMjQsNjMsMTQ3LDc1LDgyLDI0LDExNywyNDUsMjI3LDI0NywxMzcsMTI4LDE3NSwxNTEsMTAsOTEsMzAsNCw0Miw1NiwxMTgsMjE1LDc1LDE2OCwxOTAsMjM5LDIzMyw0NSwyMTAsMjI3LDExNSwyMDQsMTAwLDY1LDIwMCwxNDgsMTczLDEyNCwyMTcsMTY0LDI0NCwxMjUsMTczLDYxLDE2NCwyNTQsMTc1LDcxLDE0NSwxNTIsNzEsODMsMjI3LDkwLDgsMTU2LDIyOCwyMDMsMjI3LDIxMSwyMDAsNTQsMjM0LDIwOCwxNjIsMTY3LDIwNiwxNjUsOTcsOTAsMjE1LDIyOCw3MiwxNiwxMzQsMTk1LDExOSwxNzksMTAyLDY4LDIxMCwyNTEsMTUzLDEwNywxODgsMjI2LDE1NSwzMywxNzMsMTg0LDE0OSwyNDcsMTQ3LDEzMCwyMzgsMTk3LDczLDYyLDc4LDE5Myw1MSwxOTQsMjIsMTI3LDcsMTEzLDI0NSwxNTMsNDksMTE1LDE4LDE1LDI0MiwxNDEsODMsMjMyLDEyOSwxNjgsMTUxLDY0LDE5Myw1MCwyMDcsNjAsNTIsMTA0LDQ0LDE4NywyMDcsNzgsOTMsMTg5LDE5MywyMjQsMTI4LDM0LDMxLDEzNSw4MiwxNTMsMTI5LDY1LDIyMSwyMCwxNTAsMjE1LDE1LDU4LDE3Niw2Myw0NCw5MSwxOTIsMTI3LDE3Mw"
         }
      },
      "clientData":{
         "challenge":"dGVzdGJ5dGVz",
         "origin":"http://localhost:8080",
         "type":"webauthn.create"
      }
   }
}




Now onto authentication...

Convert base64 to array buffer

const credentialId = _base64ToArrayBuffer('');


const publicKeyCredentialRequestOptions = {
    challenge: Uint8Array.from(
        'randomString', c => c.charCodeAt(0)),
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
