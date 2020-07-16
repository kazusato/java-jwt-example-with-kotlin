import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import java.nio.charset.StandardCharsets
import java.util.*
import javax.crypto.KeyGenerator

/**
 * Example of java-jwt
 * https://github.com/auth0/java-jwt
 */
fun main() {
    // generate a signing key with javax.crypto.KeyGenerator
    val key = KeyGenerator.getInstance("HmacSHA256").generateKey()
    val base64Key = Base64.getEncoder().encode(key.encoded).toString(StandardCharsets.UTF_8)
    println("Base64 Key: ${base64Key}")

    // create a token
    val algorithm = Algorithm.HMAC256(key.encoded)
    val token = JWT.create()
        .withIssuer("dummyissuer") // generates "iss"
        .withAudience("mysimpleapp") // generates "aud"
        .withClaim("aaa", "bbb") // generates others
        .withClaim("ccc", "ddd")
        .withClaim("eee", "fff")
        .sign(algorithm)
    println(token)

    // simply decode
    // val decodedToken = JWT.decode(token)

    // or verify
    val verifier = JWT.require(Algorithm.HMAC256(key.encoded))
        .withIssuer("dummyissuer")
        .withAudience("mysimpleapp")
        .withClaim("aaa", "bbb")
        .build()
    val decodedToken = verifier.verify(token)
    println("Header: ${Base64.getDecoder().decode(decodedToken.header).toString(StandardCharsets.UTF_8)}")
    println("Payload: ${Base64.getDecoder().decode(decodedToken.payload).toString(StandardCharsets.UTF_8)}")
}
