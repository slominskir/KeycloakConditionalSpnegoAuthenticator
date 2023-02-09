package org.keycloak.extras.authentication.authenticators.browser;

import java.util.Arrays;
import java.util.List;
import org.keycloak.Config;
import org.keycloak.authentication.Authenticator;
import org.keycloak.authentication.AuthenticatorFactory;
import org.keycloak.models.AuthenticationExecutionModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.models.UserCredentialModel;
import org.keycloak.provider.ProviderConfigProperty;

import static org.keycloak.extras.authentication.authenticators.browser.ConditionalSpnegoAuthenticator.WHITELIST_PATTERN;
import static org.keycloak.provider.ProviderConfigProperty.STRING_TYPE;

public class ConditionalSpnegoAuthenticatorFactory implements AuthenticatorFactory {
    public static final String PROVIDER_ID = "conditional-auth-spnego";
    static ConditionalSpnegoAuthenticator SINGLETON = new ConditionalSpnegoAuthenticator();

    @Override
    public Authenticator create(KeycloakSession session) {
        return SINGLETON;
    }

    @Override
    public void init(Config.Scope config) {

    }

    @Override
    public void postInit(KeycloakSessionFactory factory) {

    }

    @Override
    public void close() {

    }

    @Override
    public String getId() {
        return PROVIDER_ID;
    }

    @Override
    public String getReferenceCategory() {
        return UserCredentialModel.KERBEROS;
    }

    @Override
    public boolean isConfigurable() {
        return true;
    }

    public static final AuthenticationExecutionModel.Requirement[] REQUIREMENT_CHOICES = {
            AuthenticationExecutionModel.Requirement.ALTERNATIVE,
            AuthenticationExecutionModel.Requirement.REQUIRED,
            AuthenticationExecutionModel.Requirement.DISABLED};

    @Override
    public AuthenticationExecutionModel.Requirement[] getRequirementChoices() {
        return REQUIREMENT_CHOICES;
    }

    @Override
    public String getDisplayType() {
        return "Conditional SPNEGO";
    }

    @Override
    public String getHelpText() {
        return "Conditionally attempt SPNEGO based on existence of prompt=login and optionally a configured whitelist regex pattern to match against the first X-Forwarded-For";
    }

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        ProviderConfigProperty whitelistPattern = new ProviderConfigProperty();
        whitelistPattern.setType(STRING_TYPE);
        whitelistPattern.setName(WHITELIST_PATTERN);
        whitelistPattern.setLabel("X-Forwarded-For Whitelist Regex Pattern");
        whitelistPattern.setHelpText("Whitelist Regex pattern to match against X-Forwarded-For HTTP header.  If pattern is empty SPNEGO is allowed, else only hosts matching the pattern have SPNEGO attempted.");
        whitelistPattern.setDefaultValue("");

        return Arrays.asList(whitelistPattern);
    }

    @Override
    public boolean isUserSetupAllowed() {
        return true;
    }    
}
