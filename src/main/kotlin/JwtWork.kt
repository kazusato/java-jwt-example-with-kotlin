import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm

fun main() {
    val algorithm = Algorithm.HMAC256("dummysecret")
    val token = JWT.create()
        .withIssuer("dummyissuer") // generates "iss"
        .withClaim("aaa", "bbb") // generates others
        .withClaim("ccc", "ddd")
        .withClaim("eee", "fff")
        .sign(algorithm)
    println(token)
}
