package com.study.util.code;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import org.junit.Test;

import java.security.MessageDigest;
import java.security.Security;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * sha-224 摘要，sha224 + 64摘要
 */
public class SHA224Coder {

    /**
     * SHA-224消息摘要
     * @param data 待做摘要处理的数据
     * @return byte[] 消息摘要
     * @throws Exception
     */
    public static byte[] encodeSHA224(byte[] data) throws Exception {
        // 加入BouncyCastleProvider支持
        Security.addProvider(new BouncyCastleProvider());
        // 初始化MessageDigest
        MessageDigest md = MessageDigest.getInstance("SHA-224");
        // 执行消息摘要
        return md.digest(data);
    }
    /**
     * SHA-224消息摘要
     * @param data 待做摘要处理的数据
     * @return String 十六进制消息摘要
     * @throws Exception
     */
    public static String encodeSHA224Hex(byte[] data) throws Exception {
        // 执行消息摘要
        byte[] b = encodeSHA224(data);
        // 做十六进制编码处理
        return new String(Hex.encode(b));
    }

    /**
     * 测试SHA-224
     * @throws Exception
     */
    @Test
    public final void testEncodeSHA224() throws Exception {
        String str = "SHA224消息摘要";
    // 获得摘要信息
        byte[] data1 = SHA224Coder.encodeSHA224(str.getBytes());
        byte[] data2 = SHA224Coder.encodeSHA224(str.getBytes());
        System.out.println("data1:" + new String(data1));
        System.out.println("data2:" + new String(data2));
        // 校验
        assertArrayEquals(data1, data2);
    }
        /**
     * 测试SHA-224
     * @throws Exception
     */
    @Test
    public final void testEncodeSHA224Hex() throws Exception {
        String str = "SHA224消息摘要";
        // 获得摘要信息
        String data1 = SHA224Coder.encodeSHA224Hex (str.getBytes());
        String data2 = SHA224Coder.encodeSHA224Hex (str.getBytes());
        System.out.println("data1:" + data1);
        System.out.println("data2:" + data2);
        // 校验
        assertEquals(data1, data2);
    }
}
