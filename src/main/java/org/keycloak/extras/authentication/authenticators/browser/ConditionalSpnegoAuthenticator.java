package org.keycloak.extras.authentication.authenticators.browser;

import org.jboss.logging.Logger;
import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.authenticators.browser.SpnegoAuthenticator;
import org.keycloak.sessions.AuthenticationSessionModel;

import java.util.Map;

public class ConditionalSpnegoAuthenticator extends SpnegoAuthenticator {

    private static Logger logger = Logger.getLogger(ConditionalSpnegoAuthenticator.class);

    @Override
    public void authenticate(AuthenticationFlowContext context) {
        AuthenticationSessionModel session = context.getAuthenticationSession();
        Map<String, String> clientNotes = session.getClientNotes();
        if ("login".equals(clientNotes.get("prompt"))) {
            logger.debug("Skip SPNEGO because of prompt=login");
            context.attempted();
            return;
        }

        super.authenticate(context);
    }
}