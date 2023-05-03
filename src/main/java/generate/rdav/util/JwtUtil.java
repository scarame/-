package generate.rdav.util;



import generate.rdav.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import generate.rdav.util.CONSTANT;
public class JwtUtil {
    /**
     * 用户登录成功后生成Jwt
     * 使用Hs256算法  私匙使用用户密码
     *
     //* @param ttlMillis jwt过期时间
     * @param user      登录成功的user对象
     * @return
     */
    public static String createJWT(User user) {

        //指定签名的时候使用的签名算法，jjwt已经将这部分内容封装好了。
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //生成JWT的时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        //创建payload的私有声明，用来存放实际需要传递的数据
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("id", user.getId());
        claims.put("account", user.getAccount());
        //设置服务端的私钥
        String key = CONSTANT.TOKEN_KEY;
        //生成签发人
        String subject = user.getAccount();
        //添加标准声明和私有声明
        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(now)
                .setSubject(subject)
                .signWith(signatureAlgorithm, key);
        return builder.compact();
    }



    //Token的解密
    public static Claims parseJWT(String token) {
        //签名秘钥，和生成的签名的秘钥一模一样
        String key = CONSTANT.TOKEN_KEY;
        //得到DefaultJwtParser
        Claims claims = Jwts.parser()
                //设置签名的秘钥
                .setSigningKey(key)
                //设置需要解析的jwt
                .parseClaimsJws(token).getBody();
        return claims;
    }

    /**
     * 校验token
     * 在这里可以使用官方的校验
//     * @param token
     * @return
     */
//    public static Boolean isVerify(String token) {
//        try {
//            //得到DefaultJwtParser
//            Claims claims = Jwts.parser()
//                    //设置签名的秘钥
//                    .setSigningKey("springboot_demo")
//                    //设置需要解析的jwt
//                    .parseClaimsJws(token).getBody();
//            if (claims.get("password").equals("666")) {
//                return true;
//            }
//            return false;
//        }catch (Exception e){
//            return false;
//        }
//    }
}
