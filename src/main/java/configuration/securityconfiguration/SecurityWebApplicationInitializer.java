package configuration.securityconfiguration;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Sama ta klasa (nawet jeśli jest pusta) dodaje do architektury aplikacji zestaw filtrów,
 * który wykorzystuje security.
 *
 * Konsekwencje:
 * - każdy request przechodzi przez mechanizm autentykacji,
 * - generowana jest domyślna strona logowania
 * - zapewnione jest zabezpieczenie przed atakami, np CSRF lub session-fixation
 */
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

    /**
     * enableHttpSessionEventPublisher domyślnie jest wyłączona (false)
     * Po włączeniu działa mechanizm, który nie pozwala na drugie zalogowanie się na tego samego usera.
     */
    @Override
    protected boolean enableHttpSessionEventPublisher() {
        return true;
    }
}
