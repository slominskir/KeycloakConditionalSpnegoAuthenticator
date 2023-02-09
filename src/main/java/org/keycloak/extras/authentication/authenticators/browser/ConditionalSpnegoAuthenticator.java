package org.keycloak.extras.authentication.authenticators.browser;

import org.jboss.logging.Logger;
import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.authenticators.browser.SpnegoAuthenticator;
import org.keycloak.sessions.AuthenticationSessionModel;

import java.util.Map;
import java.util.regex.Pattern;

public class ConditionalSpnegoAuthenticator extends SpnegoAuthenticator {

    private static Logger logger = Logger.getLogger(ConditionalSpnegoAuthenticator.class);

    public static final String WHITELIST_PATTERN = "XForwardedForWhitelistPattern";

    @Override
    public void authenticate(AuthenticationFlowContext context) {
        AuthenticationSessionModel session = context.getAuthenticationSession();
        Map<String, String> clientNotes = session.getClientNotes();
        if ("login".equals(clientNotes.get("prompt"))) {
            logger.debug("Skip SPNEGO because of prompt=login");
            context.attempted();
            return;
        }

        Map<String, String> config = context.getAuthenticatorConfig().getConfig();
        String patternStr = config.get(WHITELIST_PATTERN);
        String xForwardedFor = context.getHttpRequest().getHttpHeaders().getRequestHeaders().getFirst("X-Forwarded-For");

        if(!patternStr.isEmpty() || xForwardedFor != null && !xForwardedFor.isEmpty()) {
            logger.debug("Matcher pattern: " + patternStr + " ,xForwardedFor: " + xForwardedFor);
            Pattern pattern = Pattern.compile(patternStr, Pattern.DOTALL | Pattern.CASE_INSENSITIVE);

            if(!pattern.matcher(xForwardedFor).matches()) {
                logger.debug("Skip SPNEGO because X-Forwarded-For does not match configured pattern");
                context.attempted();
                return;
            }
        }

        super.authenticate(context);
    }
}