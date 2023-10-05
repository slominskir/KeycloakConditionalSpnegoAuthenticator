package org.keycloak.extras.authentication.authenticators.browser;

import org.junit.Assert;
import org.junit.Test;

public class PatternTest {

    private ConditionalSpnegoAuthenticator authenticator = ConditionalSpnegoAuthenticatorFactory.SINGLETON;

    private String anyPatternStr = "192\\.168.*";
    private String rangePatternStr = "(172\\.1[6-9].*)|(172\\.2[0-9].*)|(172\\.3[0-1].*)";

    @Test
    public void testMatchAnyPattern() {
        String xForwardedFor = "192.168.1.2";

        Assert.assertTrue(authenticator.inWhitelist(anyPatternStr, xForwardedFor));
    }

    @Test
    public void testNoMatchAnyPattern() {
        String xForwardedFor = "10.0.0.2";

        Assert.assertFalse(authenticator.inWhitelist(anyPatternStr, xForwardedFor));
    }

    @Test
    public void testMatchRangePattern() {
        String xForwardedFor = "172.16.0.2";

        Assert.assertTrue(authenticator.inWhitelist(rangePatternStr, xForwardedFor));
    }

    @Test
    public void testNoMatchRangePattern() {
        String xForwardedFor = "172.32.0.2";

        Assert.assertFalse(authenticator.inWhitelist(rangePatternStr, xForwardedFor));
    }
}
