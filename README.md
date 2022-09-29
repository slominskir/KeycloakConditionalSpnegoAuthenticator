# KeycloakConditionalSpnegoAuthenticator
A Keycloak authenticator that allows Conditional SPNEGO based on presence of `prompt=login`.

## Install
1. Obtain the plugin jar file by either downloading it from the [releases](https://github.com/slominskir/KeycloakConditionalSpnegoAuthenticator/releases) page, or [building](https://github.com/slominskir/KeycloakConditionalSpnegoAuthenticator#build) it yourself.
2. Drop the jar file into the `providers` sub directory in the Keycloak home directory.

## Build
```
mvn package
```


## See Also
- [KeycloakAutoLinkAuthenticator](https://github.com/slominskir/KeycloakAutoLinkAuthenticator)
