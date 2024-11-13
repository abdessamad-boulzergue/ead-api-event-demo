"# ead-api-event-demo" 
 
 TLS/convert .crt to PKCS12
-

* verify wether the .key is encrypted l> *cat tls.key*
  - ENCRYPTED PRIVATE KEY → encrypted key
  - PRIVATE KEY → key not encrypted


- remove password encryption: 
  - l> *openssl rsa -in tls.key -out tls_unencrypted.key*
  * create PKCS12 file  l> 
  -openssl pkcs12 -export -in tls.crt -inkey tls.key \
  -out keystore.p12 -name "alias_name" -password pass:your_password