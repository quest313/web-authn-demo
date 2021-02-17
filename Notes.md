/// Oh maybe some made my server side life easier! should i peek

https://github.com/webauthn4j/webauthn4j

Japanese guy... is this cheating?


What are FIDO Conformance test tools

Wine guys makes COSE tools...


Yubico has an implementation
https://mvnrepository.com/artifact/com.yubico/webauthn-server-core



More to try
-- What happens in different browsers


Need to read more into this  
https://chromium.googlesource.com/chromium/src/+/master/content/browser/webauth/uv_preferred.md


Finally able to get it working from the javascript perspective....

Used the javascript bytes.buffer which was taken from  the decodedAttestationObject...



// don't use!!!!!!!!!!!!!!!!! instead use function in base64ArrayBuffer.js
window.btoa(decodedAttestationObj.authData)
window.btoa(decodedAttestationObj.attStmt.sig)
window.btoa(decodedAttestationObj.attStmt.x5c)



// Be careful of the order of elements in clientDataJson as this could fail signature





---- 

Theoretically if I create an ECParameterspec with both points I should be able to "reconstruct" my public key


--- 

https://developers.yubico.com/yubico-piv-tool/

This could be useful

Good video on ECC from Computherphile


Could credential id's intersect? Meaning different users save credential id? Protect against this int the backend


Good visual for presentation

https://w3c.github.io/webauthn/#sctn-op-get-assertion

Buddy slide for all the steps to validate during authentication
https://www.pinterest.com/pin/9640586677482052/?autologin=true&nic_v1=1ac4MKvQC6tX9QQc6vN7tSO1WWiNo2Jby322nmLgeBnoJ%2FTclFq3xsGo2srXteOTS0




