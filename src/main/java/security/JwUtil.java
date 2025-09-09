package security;

import java.security.Key;
import java.security.KeyStore;
import java.security.Signature;

public class JwUtil {

    private static final Key  key = KeyStore.secretKeyFor(SignatureAlgorithm.HS256);
}
