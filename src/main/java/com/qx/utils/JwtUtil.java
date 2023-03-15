package com.qx.utils;

import com.qx.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * JWT工具类
 */
public class JwtUtil {

    //有效期为
    public static final Long JWT_TTL = 60 * 60 *1000L;// 60 * 60 *1000  一个小时
    //设置秘钥明文
    public static final String JWT_KEY = "qx";

    public static String getUUID(){
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        return token;
    }
    
    /**
     * 生成jwt
     * @param subject token中要存放的数据（json格式）
     * @return
     */
    public static String createJWT(String subject) {
        JwtBuilder builder = getJwtBuilder(subject, null, getUUID());// 设置过期时间
        return builder.compact();
    }

    /**
     * 生成jwt
     * @param subject token中要存放的数据（json格式）
     * @param ttlMillis token超时时间
     * @return
     */
    public static String createJWT(String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, getUUID());// 设置过期时间
        return builder.compact();
    }


    /**
     * 生成jwt token
     * @param id jwt的唯一标识
     * @param subject token中要存放的数据（json格式）
     * @param ttlMillis token超时时间
     * @return
     */
    public static String createJWT(String id, String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, id);// 设置过期时间
        return builder.compact();
    }

    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if(ttlMillis==null){
            ttlMillis=JwtUtil.JWT_TTL;
        }
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        return Jwts.builder()
                .setId(uuid)              //唯一的ID
                .setSubject(subject)   // 主题  可以是JSON数据
                .setIssuer("sg")     // 签发者
                .setIssuedAt(now)      // 签发时间
                .signWith(signatureAlgorithm, secretKey) //使用HS256对称加密算法签名, 第二个参数为秘钥
                .setExpiration(expDate);
    }


    public static void main(String[] args) throws Exception {
        User user = new User();
        user.setUserName("张三");
        user.setPassword("test");
        String jwt = createJWT("1001",user.toString(), 3600000L);
        System.out.println(jwt);


//        Claims claims = parseJWT("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMDAxIiwic3ViIjoiVXNlcihpZD1udWxsLCB1c2VyTmFtZT3lvKDkuIksIG5pY2tOYW1lPW51bGwsIHBhc3N3b3JkPXRlc3QsIHN0YXR1cz1udWxsLCBlbWFpbD1udWxsLCBwaG9uZW51bWJlcj1udWxsLCBzZXg9bnVsbCwgYXZhdGFyPW51bGwsIHVzZXJUeXBlPW51bGwsIGNyZWF0ZUJ5PW51bGwsIGNyZWF0ZVRpbWU9bnVsbCwgdXBkYXRlQnk9bnVsbCwgdXBkYXRlVGltZT1udWxsLCBkZWxGbGFnPW51bGwpIiwiaXNzIjoic2ciLCJpYXQiOjE2Nzg3OTcwOTksImV4cCI6MTY3ODc5NzEwMH0.BcbceNKr78esEpLkVv-sK8hCsvD9SQ3fdcsmtD2dwVk");
        Claims claims = parseJWT("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMDAxIiwic3ViIjoiVXNlcihpZD1udWxsLCB1c2VyTmFtZT3lvKDkuIksIG5pY2tOYW1lPW51bGwsIHBhc3N3b3JkPXRlc3QsIHN0YXR1cz1udWxsLCBlbWFpbD1udWxsLCBwaG9uZW51bWJlcj1udWxsLCBzZXg9bnVsbCwgYXZhdGFyPW51bGwsIHVzZXJUeXBlPW51bGwsIGNyZWF0ZUJ5PW51bGwsIGNyZWF0ZVRpbWU9bnVsbCwgdXBkYXRlQnk9bnVsbCwgdXBkYXRlVGltZT1udWxsLCBkZWxGbGFnPW51bGwpIiwiaXNzIjoic2ciLCJpYXQiOjE2Nzg3OTcyNTQsImV4cCI6MTY3ODgwMDg1NH0.U6v4gMPtk7beRBv8UBkYn9CZQGONp1OwmFyETCh6m1w");
        String subject = claims.getSubject();
        System.out.println(subject);

//        System.out.println(claims);
    }

    /**
     * 生成加密后的秘钥 secretKey
     * @return
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }
    
    /**
     * 解析
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }


}