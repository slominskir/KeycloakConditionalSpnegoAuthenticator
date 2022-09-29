# KeycloakConditionalSpnegoAuthenticator
A Keycloak authenticator that allows conditional SPNEGO based on presence of `prompt=login`.  Tested with Keycloak 19.0.2.  See: https://github.com/keycloak/keycloak/issues/8989.

## Install
1. Obtain the plugin jar file by either downloading it from the [releases](https://github.com/slominskir/KeycloakConditionalSpnegoAuthenticator/releases) page, or [building](https://github.com/slominskir/KeycloakConditionalSpnegoAuthenticator#build) it yourself.
2. Drop the jar file into the `providers` sub directory in the Keycloak home directory.
3. If running Keycloak in production mode you'll need to execute the `kc.sh build`, before starting Keycloak

## Configure
1. Create a new browser flow (easiest way is to duplicate the built-in one)
2. "bind" the new flow as the default browser flow
3. Edit the new flow by removing the existing "Kerberos" step/execution
4. Add the new execution "Conditional SPNEGO"
5. Re-order the new execution to where the old "Kerberos one was in the list

## Build
Built with Maven and Java 11.

```
git clone https://github.com/slominskir/KeycloakConditionalSpnegoAuthenticator.git
cd KeycloakConditionalSpnegoAuthenticator
mvn package
```


## See Also
- [KeycloakAutoLinkAuthenticator](https://github.com/slominskir/KeycloakAutoLinkAuthenticator)
