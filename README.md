# KeycloakConditionalSpnegoAuthenticator
A Keycloak authenticator that allows conditional SPNEGO based on presence of `prompt=login`.  Tested with Keycloak 19.0.2.  See: https://github.com/keycloak/keycloak/issues/8989.

## Install
1. Obtain the plugin jar file by either downloading it from the [releases](https://github.com/slominskir/KeycloakConditionalSpnegoAuthenticator/releases) page, or [building](https://github.com/slominskir/KeycloakConditionalSpnegoAuthenticator#build) it yourself.
2. Drop the jar file into the `providers` sub directory in the Keycloak home directory.
3. If running Keycloak in production mode you'll likely need to execute the `kc.sh build`, before starting Keycloak

## Build
Built with Maven and Java 11.

```
mvn package
```


## See Also
- [KeycloakAutoLinkAuthenticator](https://github.com/slominskir/KeycloakAutoLinkAuthenticator)
