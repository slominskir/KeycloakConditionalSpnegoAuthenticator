# KeycloakConditionalSpnegoAuthenticator
A Keycloak authenticator that allows conditional SPNEGO based on presence of `prompt=login`.  Tested with Keycloak 19.0.2.  See: https://github.com/keycloak/keycloak/issues/8989.

## Overview
It is often desirable to treat Intranet (internal) users with the convenience of Kerberos Single Sign On, while preventing Internet (external) users from possibly seeing a confusing NTLM prompt dialog.  The often undesirable prompt occurs because Kerberos is only available bundled with SPNEGO (www-authenticate: Negotiate), which has an implicit fallback on NTLM, which is honored in a browser specific way with [differing default behaviors](https://github.com/keycloak/keycloak/issues/16981).

It can also be useful to allow users to explicitly opt out of SPNEGO in order to provide a switch user functionality in the scenario the user has multiple accounts, such as an admin or group/service account on a kiosk.  It is also possible that Keycloak is serving users in multiple Kerberos realms that are not in a cross realm trust, and in this case some SPNEGO failures would be expected so Keycloak's JBoss logging log level would need to be set to avoid the enormous stack trace on each failure, or this authenticator can be configured to skip SPNEGO on subnets other than those served by the expected KDC.

## Usage
This Keycloak authenticator allows SPNEGO authentication to be skipped by explict client request and optionally by request IP.  Clients can request SPNEGO authentication be skipped via the URL `prompt=login` present in the client request.  A `Whitelist Regex Pattern` can optionally be configured, and if configured, it is compared against the `X-Forwarded-For` header on auth requests to check for a match.  If no match then no SPNEGO is skipped. 

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
6. Optionally edit the ProviderConfigProperty `Whitelist Regex Pattern`

## Build
Built with Maven and Java 11.

```
git clone https://github.com/slominskir/KeycloakConditionalSpnegoAuthenticator.git
cd KeycloakConditionalSpnegoAuthenticator
mvn package
```


## See Also
- [KeycloakAutoLinkAuthenticator](https://github.com/slominskir/KeycloakAutoLinkAuthenticator)
