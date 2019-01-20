package configuration.appconfiguration;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {
    /**
     * Sama ta klasa (nawet jeśli jest pusta) dodaje do architektury aplikacji zestaw filtrów,
     * który wykorzystuje security.
     *
     * Konsekwencje:
     * - każdy request przechodzi przez mechanizm autentykacji,
     * - generowana jest domyślna strona logowania
     * - zapewnione jest zabezpieczenie przed atakami, np CSRF lub session-fixation
     */
}
