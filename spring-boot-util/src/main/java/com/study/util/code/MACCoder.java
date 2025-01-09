package com.study.util.code;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import org.junit.Test;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import java.security.Security;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MACCoder {
    /**
     * 初始化HmacMD5密钥
     * @return byte[] 密钥
     * @throws Exception
     */
    public static byte[] initHmacMD5Key() throws Exception {
        // 初始化KeyGenerator
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD5");
        // 产生密钥
        SecretKey secretKey = keyGenerator.generateKey();
        // 获得密钥
        return secretKey.getEncoded();
    }
    /**
     * HmacMD5消息摘要
     * @param data 待做摘要处理的数据
     * @param key 密钥
     * @return byte[] 消息摘要
     * @throws Exception
     */
    public static byte[] encodeHmacMD5(byte[] data, byte[] key)
            throws Exception {
        // 还原密钥
        SecretKey secretKey = new SecretKeySpec(key, "HmacMD5");
        // 实例化Mac
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        // 初始化Mac
        mac.init(secretKey);
        // 执行消息摘要
        return mac.doFinal(data);
    }
    /**
     * 初始化HmacSHA1密钥
     * @return byte[] 密钥
     * @throws Exception
    184 Java加密与解密的艺术
    华章IT 15周年，《Java加密与解密的艺术》互动网首发http:		//www.china-pub.com/196506
     */
    public static byte[] initHmacSHAKey() throws Exception {
        // 初始化KeyGenerator
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA1");
        // 产生密钥
        SecretKey secretKey = keyGenerator.generateKey();
        // 获得密钥
        return secretKey.getEncoded();
    }
    /**
     * HmacSHA1消息摘要
     * @param data 待做摘要处理的数据
     * @param key 密钥
     * @return byte[] 消息摘要
     * @throws Exception
     */
    public static byte[] encodeHmacSHA(byte[] data, byte[] key)
            throws Exception {
        // 还原密钥
        SecretKey secretKey = new SecretKeySpec(key, "HmacSHA1");
        // 实例化Mac
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        // 初始化Mac
        mac.init(secretKey);
        // 执行消息摘要
        return mac.doFinal(data);
    }
    /**
     * 初始化HmacSHA256密钥
     * @return byte[] 密钥
     * @throws Exception
     */
    public static byte[] initHmacSHA256Key() throws Exception {
        // 初始化KeyGenerator
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
        // 产生密钥
        SecretKey secretKey = keyGenerator.generateKey();
        // 获得密钥
        return secretKey.getEncoded();
    }
    /**
     * HmacSHA256消息摘要
     * @param data 待做摘要处理的数据
     * @param key 密钥
     * @return byte[] 消息摘要
     * @throws Exception
     */
    public static byte[] encodeHmacSHA256(byte[] data, byte[] key) throws Exception {
        // 还原密钥
        SecretKey secretKey = new SecretKeySpec(key, "HmacSHA256");
        // 实例化Mac
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        // 初始化Mac
        mac.init(secretKey);
        // 执行消息摘要
        return mac.doFinal(data);
    }
    /**
     * 初始化HmacSHA384密钥
     * @return byte[] 密钥
     * @throws Exception
     */
    public static byte[] initHmacSHA384Key() throws Exception {
        // 初始化KeyGenerator
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA384");
        // 产生密钥
        SecretKey secretKey = keyGenerator.generateKey();
        // 获得密钥
        return secretKey.getEncoded();
    }
    /**
     * HmacSHA384消息摘要
     * @param data 待做摘要处理的数据
     * @param key 密钥
     * @return byte[] 消息摘要
     * @throws Exception
     */
    public static byte[] encodeHmacSHA384(byte[] data, byte[] key) throws Exception {
        // 还原密钥
        SecretKey secretKey = new SecretKeySpec(key, "HmacSHA384");
        // 实例化Mac
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        // 初始化Mac
        mac.init(secretKey);
        // 执行消息摘要
        return mac.doFinal(data);
    }
    /**
     * 初始化HmacSHA512密钥
     * @return byte[] 密钥
     * @throws Exception
     */
    public static byte[] initHmacSHA512Key() throws Exception {
        // 初始化KeyGenerator
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA512");
        // 产生密钥
        SecretKey secretKey = keyGenerator.generateKey();
        // 获得密钥
        return secretKey.getEncoded();
    }
    /**
     * HmacSHA512消息摘要
     * @param data 待做摘要处理的数据
     * @param key 密钥
     * @return byte[] 消息摘要
     * @throws Exception
     */
    public static byte[] encodeHmacSHA512(byte[] data, byte[] key) throws Exception {
        // 还原密钥
        SecretKey secretKey = new SecretKeySpec(key, "HmacSHA512");
        // 实例化Mac
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        // 初始化Mac
        mac.init(secretKey);
        // 执行消息摘要
        return mac.doFinal(data);
    }

    /**
     * 初始化HmacMD2密钥
     * @return byte[] 密钥
     * @throws Exception
     */
    public static byte[] initHmacMD2Key() throws Exception {
        // 加入BouncyCastleProvider支持
        Security.addProvider(new BouncyCastleProvider());
        // 初始化KeyGenerator
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD2");
        // 产生秘密密钥
        SecretKey secretKey = keyGenerator.generateKey();
        // 获得密钥
        return secretKey.getEncoded();
    }

    /**
     * HmacMD2消息摘要
     * @param data 待做消息摘要处理的数据
     * @param key 密钥
     * @return String 消息摘要
     * @throws Exception
     */
    public static byte[] encodeHmacMD2(byte[] data, byte[] key) throws Exception {
        // 加入BouncyCastleProvider支持
        Security.addProvider(new BouncyCastleProvider());
        // 还原密钥
        SecretKey secretKey = new SecretKeySpec(key, "HmacMD2");
        // 实例化Mac
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        // 初始化Mac
        mac.init(secretKey);
        // 执行消息摘要
        return mac.doFinal(data);
    }

    /**
     * HmacMD2Hex消息摘要
     * @param data 待做消息摘要处理的数据
     * @param key 密钥
     * @return byte[] 消息摘要
     * @throws Exception
     */
    public static String encodeHmacMD2Hex(byte[] data, byte[] key) throws Exception {
        // 执行消息摘要
        byte[] b = encodeHmacMD2(data, key);
        // 做十六进制转换
        return new String(Hex.encode(b));
    }

    /**
     * 初始化HmacMD4密钥
     * @return byte[] 密钥
     * @throws Exception
     */
    public static byte[] initHmacMD4Key() throws Exception {
        // 加入BouncyCastleProvider支持
        Security.addProvider(new BouncyCastleProvider());
        // 初始化KeyGenerator
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD4");
        // 产生秘密密钥
        SecretKey secretKey = keyGenerator.generateKey();
        // 获得密钥
        return secretKey.getEncoded();
    }

    /**
     * HmacMD4消息摘要
     * @param data 待做消息摘要处理的数据
     * @param key 密钥
     * @return byte[] 消息摘要
     * @throws Exception
     */
    public static byte[] encodeHmacMD4(byte[] data, byte[] key) throws Exception {
        // 加入BouncyCastleProvider支持
        Security.addProvider(new BouncyCastleProvider());
        // 还原密钥
        SecretKey secretKey = new SecretKeySpec(key, "HmacMD4");
        // 实例化Mac
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        // 初始化Mac
        mac.init(secretKey);
        // 执行消息摘要
        return mac.doFinal(data);
    }

    /**
     * HmacMD4Hex消息摘要
     * @param data 待做消息摘要处理的数据
     * @param key 密钥
     * @return String 消息摘要
     * @throws Exception
     */
    public static String encodeHmacMD4Hex(byte[] data, byte[] key) throws Exception {
        // 执行消息摘要
        byte[] b = encodeHmacMD4(data, key);
        // 做十六进制转换
        return new String(Hex.encode(b));
    }

    /**
     * 初始化HmacSHA224密钥
     * @return byte[] 密钥
     * @throws Exception
     */
    public static byte[] initHmacSHA224Key() throws Exception {
        // 加入BouncyCastleProvider支持
        Security.addProvider(new BouncyCastleProvider());
        // 初始化KeyGenerator
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA224");
        // 产生秘密密钥
        SecretKey secretKey = keyGenerator.generateKey();
        // 获得密钥
        return secretKey.getEncoded();
    }

    /**
     * HmacSHA224消息摘要
     * @param data 待做消息摘要处理的数据
     * @param key 密钥
     * @return byte[] 消息摘要
     * @throws Exception
     */
    public static byte[] encodeHmacSHA224(byte[] data, byte[] key) throws Exception {
        // 加入BouncyCastleProvider支持
        Security.addProvider(new BouncyCastleProvider());
        // 还原密钥
        SecretKey secretKey = new SecretKeySpec(key, "HmacSHA224");
        // 实例化Mac
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        // 初始化Mac
        mac.init(secretKey);
        // 执行消息摘要
        return mac.doFinal(data);
    }

    /**
     * HmacSHA224Hex消息摘要
     * @param data 待做消息摘要处理的数据
     * @param key 密钥
     * @return String 消息摘要
     * @throws Exception
     */
    public static String encodeHmacSHA224Hex(byte[] data, byte[] key) throws Exception {
        // 执行消息摘要
        byte[] b = encodeHmacSHA224(data, key);
        // 做十六进制转换
        return new String(Hex.encode(b));
    }

    /**
     * 测试HmacMD5
     * @throws Exception
     */
    @Test
    public final void testEncodeHmacMD5() throws Exception {
        String str = "HmacMD5消息摘要";
        // 初始化密钥
        byte[] key = MACCoder.initHmacMD5Key();
        // 获得摘要信息
        byte[] data1 = MACCoder.encodeHmacMD5(str.getBytes(), key);
        byte[] data2 = MACCoder.encodeHmacMD5(str.getBytes(), key);
        // 校验
        assertArrayEquals(data1, data2);
    }

    /**
     * 测试HmacSHA1
     * @throws Exception
     */
    @Test
    public final void testEncodeHmacSHA() throws Exception {
        String str = "HmacSHA1消息摘要";
        // 初始化密钥
        byte[] key = MACCoder.initHmacSHAKey();
        // 获得摘要信息
        byte[] data1 = MACCoder.encodeHmacSHA(str.getBytes(), key);
        byte[] data2 = MACCoder.encodeHmacSHA(str.getBytes(), key);
        // 校验
        assertArrayEquals(data1, data2);
    }

    /**
     * 测试HmacSHA256
     * @throws Exception
     */
    @Test
    public final void testEncodeHmacSHA256() throws Exception {
        String str = "HmacSHA256消息摘要";
        // 初始化密钥
        byte[] key = MACCoder.initHmacSHA256Key();
        // 获得摘要信息
        byte[] data1 = MACCoder.encodeHmacSHA256(str.getBytes(), key);
        byte[] data2 = MACCoder.encodeHmacSHA256(str.getBytes(), key);
        // 校验
        assertArrayEquals(data1, data2);
    }

    /**
     * 测试HmacSHA384
     * @throws Exception
     */
    @Test
    public final void testEncodeHmacSHA384() throws Exception {
        String str = "HmacSHA384消息摘要";
        // 初始化密钥
        byte[] key = MACCoder.initHmacSHA384Key();
        // 获得摘要信息
        byte[] data1 = MACCoder.encodeHmacSHA384(str.getBytes(), key);
        byte[] data2 = MACCoder.encodeHmacSHA384(str.getBytes(), key);
        // 校验
        assertArrayEquals(data1, data2);
    }

    /**
     * 测试HmacSHA512
     * @throws Exception
     */
    @Test
    public final void testEncodeHmacSHA512() throws Exception {
        String str = "HmacSHA512消息摘要";
        // 初始化密钥
        byte[] key = MACCoder.initHmacSHA512Key();
        // 获得摘要信息
        byte[] data1 = MACCoder.encodeHmacSHA512(str.getBytes(), key);
        byte[] data2 = MACCoder.encodeHmacSHA512(str.getBytes(), key);
        // 校验
        assertArrayEquals(data1, data2);
    }

    /**
     * 测试HmacMD2
     * @throws Exception
     */
    @Test
    public void testEncodeHmacMD2() throws Exception {
        String str = "HmacMD2消息摘要";
        // 初始化密钥
        byte[] key = MACCoder.initHmacMD2Key();
        // 获得摘要信息
        byte[] data1 = MACCoder.encodeHmacMD2(str.getBytes(), key);
        byte[] data2 = MACCoder.encodeHmacMD2(str.getBytes(), key);
        // 校验
        assertArrayEquals(data1, data2);
    }

    /**
     * 测试HmacMD4
     * @throws Exception
     */
    @Test
    public final void testEncodeHmacMD4() throws Exception {
        String str = "HmacMD4消息摘要";
        // 初始化密钥
        byte[] key = MACCoder.initHmacMD4Key();
        // 获得摘要信息
        byte[] data1 = MACCoder.encodeHmacMD4(str.getBytes(), key);
        byte[] data2 = MACCoder.encodeHmacMD4(str.getBytes(), key);
        // 校验
        assertArrayEquals(data1, data2);
    }
    /**
     * 测试HmacSHA224
     * @throws Exception
     */
    @Test
    public final void testEncodeHmacSHA224() throws Exception {
        String str = "HmacSHA224消息摘要";
        // 初始化密钥
        byte[] key = MACCoder.initHmacSHA224Key();
        // 获得摘要信息
        byte[] data1 = MACCoder.encodeHmacSHA224(str.getBytes(), key);
        byte[] data2 = MACCoder.encodeHmacSHA224(str.getBytes(), key);
        // 校验
        assertArrayEquals(data1, data2);
    }


    /**
     * testEncodeHmacMD2Hex
     * @throws Exception
     */
    @Test
    public void testEncodeHmacMD2Hex() throws Exception {
        String str = "HmacMD2消息摘要";
        // 初始化密钥
        byte[] key = MACCoder.initHmacMD2Key();
        // 获得摘要信息
        String data1 = MACCoder.encodeHmacMD2Hex(str.getBytes(), key);
        String data2 = MACCoder.encodeHmacMD2Hex(str.getBytes(), key);

        System.out.println("data1："+data1);
        System.out.println("data2："+data2);

        // 校验
        assertEquals(data1, data2);
    }

    /**
     * 测试HmacMD4
     * @throws Exception
     */
    @Test
    public final void testEncodeHmacMD4Hex() throws Exception {
        String str = "HmacMD4Hex消息摘要";
        // 初始化密钥
        byte[] key = MACCoder.initHmacMD4Key();
        // 获得摘要信息
        String data1 = MACCoder.encodeHmacMD4Hex(str.getBytes(), key);
        String data2 = MACCoder.encodeHmacMD4Hex(str.getBytes(), key);

        System.out.println("data1："+data1);
        System.out.println("data2："+data2);
        // 校验
        assertEquals(data1, data2);
    }
    /**
     * 测试HmacSHA224
     * @throws Exception
     */
    @Test
    public final void testEncodeHmacSHA224Hex() throws Exception {
        String str = "HmacSHA224Hex消息摘要";
        // 初始化密钥
        byte[] key = MACCoder.initHmacSHA224Key();
        // 获得摘要信息
        String data1 = MACCoder.encodeHmacSHA224Hex(str.getBytes(), key);
        String data2 = MACCoder.encodeHmacSHA224Hex(str.getBytes(), key);
        System.out.println("data1："+data1);
        System.out.println("data2："+data2);
        // 校验
        assertEquals(data1, data2);
    }


}
