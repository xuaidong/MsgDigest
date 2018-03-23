package test;

import cn.MsgDigest;
import cn.hash.md.*;
import cn.hash.sha.impl.*;
import cn.util.ConvertUtils;
import org.junit.jupiter.api.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//ConvertUtils接口中包含了两个转换的默认方法，可以很方便的转换内容
public class Demo implements ConvertUtils {

    //待测字符串
    private final String str = "·1234567890-=【】、；‘，。、~~!@#$%^&*()_+P{}ASHHJ前为额日条与uint牌哦くぇちゅいおぱ:";

    @Test
    public void test() throws NoSuchAlgorithmException {
        System.out.println("CRC32：");
        testCRC32();
        System.out.println("CRC32C：");
        testCRC32C();
        System.out.println("MD2：");
        testMD2();
        System.out.println("MD4：");
        testMD4();
        System.out.println("MD5：");
        testMD5();
        System.out.println("SHA1：");
        testSHA1();
        System.out.println("SHA224：");
        testSHA224();
        System.out.println("SHA256：");
        testSHA256();
        System.out.println("SHA384：");
        testSHA384();
        System.out.println("SHA512：");
        testSHA512();
        System.out.println("SHA512-224：");
        testSHA512_224();
        System.out.println("SHA512-256：");
        testSHA512_256();
        System.out.println("SHA3-224：");
        testSHA3_224();
        System.out.println("SHA3-256：");
        testSHA3_256();
        System.out.println("SHA3-384：");
        testSHA3_384();
        System.out.println("SHA3-512：");
        testSHA3_384();
    }


    @Test
    public void testCRC32() {
        //JDK原生方法
        java.util.zip.CRC32 crc32 = new java.util.zip.CRC32();
        crc32.update(str.getBytes());
        //支持分段计算
        crc32.update(str.getBytes());
        //自定义byte数组转String方法，long转byte数组方法
        System.out.println(bytes2hex(long2bytes(crc32.getValue())));

        //创建对象
        MsgDigest MsgDigest = new cn.crc.impl.CRC32();
        //更新摘要
        MsgDigest.update(str.getBytes());
        //支持分段计算，接着上一次结果继续更新
        MsgDigest.update(str.getBytes());
        //重写toString方法，直接输出十六进制字符串
        System.out.println(MsgDigest);
        //重置状态
        MsgDigest.reset();
        MsgDigest.update((str + str).getBytes());
        //获取结果的字节数组，然后进行转换
        System.out.println(bytes2hex(MsgDigest.digest()));
    }

    @Test
    public void testCRC32C() {
        java.util.zip.CRC32C crc32 = new java.util.zip.CRC32C();
        crc32.update(str.getBytes());
        crc32.update(str.getBytes());
        System.out.println(bytes2hex(long2bytes(crc32.getValue())));

        MsgDigest MsgDigest = new cn.crc.impl.CRC32C();
        MsgDigest.update(str.getBytes());
        MsgDigest.update(str.getBytes());
        System.out.println(MsgDigest);
        MsgDigest.reset();
        MsgDigest.update((str + str).getBytes());
        System.out.println(bytes2hex(MsgDigest.digest()));
    }

    @Test
    public void testMD2() throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("md2");
        messageDigest.update(str.getBytes());
        messageDigest.update(str.getBytes());
        System.out.println(bytes2hex(messageDigest.digest()));

        MsgDigest MsgDigest = new MD2();
        MsgDigest.update(str.getBytes());
        MsgDigest.update(str.getBytes());
        System.out.println(MsgDigest);
        MsgDigest.reset();
        MsgDigest.update((str + str).getBytes());
        System.out.println(bytes2hex(MsgDigest.digest()));
    }

    @Test
    public void testMD4() throws NoSuchAlgorithmException {
        //JDK不支持MD4算法，而且该算法已经被淘汰，被MD5取代
        MsgDigest MsgDigest = new MD4();
        MsgDigest.update(str.getBytes());
        MsgDigest.update(str.getBytes());
        System.out.println(MsgDigest);
        MsgDigest.reset();
        MsgDigest.update((str + str).getBytes());
        System.out.println(bytes2hex(MsgDigest.digest()));
    }

    @Test
    public void testMD5() throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("md5");
        messageDigest.update(str.getBytes());
        messageDigest.update(str.getBytes());
        System.out.println(bytes2hex(messageDigest.digest()));

        MsgDigest MsgDigest = new MD5();
        MsgDigest.update(str.getBytes());
        MsgDigest.update(str.getBytes());
        System.out.println(MsgDigest);
        MsgDigest.reset();
        MsgDigest.update((str + str).getBytes());
        System.out.println(bytes2hex(MsgDigest.digest()));
    }

    @Test
    public void testSHA1() throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
        messageDigest.update(str.getBytes());
        messageDigest.update(str.getBytes());
        System.out.println(bytes2hex(messageDigest.digest()));

        MsgDigest MsgDigest = new SHA1();
        MsgDigest.update(str.getBytes());
        MsgDigest.update(str.getBytes());
        System.out.println(MsgDigest);
        MsgDigest.reset();
        MsgDigest.update((str + str).getBytes());
        System.out.println(bytes2hex(MsgDigest.digest()));
    }

    @Test
    public void testSHA224() throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-224");
        messageDigest.update(str.getBytes());
        messageDigest.update(str.getBytes());
        System.out.println(bytes2hex(messageDigest.digest()));

        MsgDigest MsgDigest = new SHA224();
        MsgDigest.update(str.getBytes());
        MsgDigest.update(str.getBytes());
        System.out.println(MsgDigest);
        MsgDigest.reset();
        MsgDigest.update((str + str).getBytes());
        System.out.println(bytes2hex(MsgDigest.digest()));
    }

    @Test
    public void testSHA256() throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(str.getBytes());
        messageDigest.update(str.getBytes());
        System.out.println(bytes2hex(messageDigest.digest()));

        MsgDigest MsgDigest = new SHA256();
        MsgDigest.update(str.getBytes());
        MsgDigest.update(str.getBytes());
        System.out.println(MsgDigest);
        MsgDigest.reset();
        MsgDigest.update((str + str).getBytes());
        System.out.println(bytes2hex(MsgDigest.digest()));
    }

    @Test
    public void testSHA384() throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-384");
        messageDigest.update(str.getBytes());
        messageDigest.update(str.getBytes());
        System.out.println(bytes2hex(messageDigest.digest()));

        MsgDigest MsgDigest = new SHA384();
        MsgDigest.update(str.getBytes());
        MsgDigest.update(str.getBytes());
        System.out.println(MsgDigest);
        MsgDigest.reset();
        MsgDigest.update((str + str).getBytes());
        System.out.println(bytes2hex(MsgDigest.digest()));
    }

    @Test
    public void testSHA512() throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
        messageDigest.update(str.getBytes());
        messageDigest.update(str.getBytes());
        System.out.println(bytes2hex(messageDigest.digest()));

        MsgDigest MsgDigest = new SHA512();
        MsgDigest.update(str.getBytes());
        MsgDigest.update(str.getBytes());
        System.out.println(MsgDigest);
        MsgDigest.reset();
        MsgDigest.update((str + str).getBytes());
        System.out.println(bytes2hex(MsgDigest.digest()));
    }

    @Test
    public void testSHA512_224() throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-512/224");
        messageDigest.update(str.getBytes());
        messageDigest.update(str.getBytes());
        System.out.println(bytes2hex(messageDigest.digest()));

        MsgDigest MsgDigest = new SHA512_224();
        MsgDigest.update(str.getBytes());
        MsgDigest.update(str.getBytes());
        System.out.println(MsgDigest);
        MsgDigest.reset();
        MsgDigest.update((str + str).getBytes());
        System.out.println(bytes2hex(MsgDigest.digest()));
    }

    @Test
    public void testSHA512_256() throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-512/256");
        messageDigest.update(str.getBytes());
        messageDigest.update(str.getBytes());
        System.out.println(bytes2hex(messageDigest.digest()));

        MsgDigest MsgDigest = new SHA512_256();
        MsgDigest.update(str.getBytes());
        MsgDigest.update(str.getBytes());
        System.out.println(MsgDigest);
        MsgDigest.reset();
        MsgDigest.update((str + str).getBytes());
        System.out.println(bytes2hex(MsgDigest.digest()));
    }

    @Test
    public void testSHA3_224() throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA3-224");
        messageDigest.update(str.getBytes());
        messageDigest.update(str.getBytes());
        System.out.println(bytes2hex(messageDigest.digest()));

        MsgDigest MsgDigest = new SHA3_224();
        MsgDigest.update(str.getBytes());
        MsgDigest.update(str.getBytes());
        System.out.println(MsgDigest);
        MsgDigest.reset();
        MsgDigest.update((str + str).getBytes());
        System.out.println(bytes2hex(MsgDigest.digest()));
    }

    @Test
    public void testSHA3_256() throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA3-256");
        messageDigest.update(str.getBytes());
        messageDigest.update(str.getBytes());
        System.out.println(bytes2hex(messageDigest.digest()));

        MsgDigest MsgDigest = new SHA3_256();
        MsgDigest.update(str.getBytes());
        MsgDigest.update(str.getBytes());
        System.out.println(MsgDigest);
        MsgDigest.reset();
        MsgDigest.update((str + str).getBytes());
        System.out.println(bytes2hex(MsgDigest.digest()));
    }

    @Test
    public void testSHA3_384() throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA3-384");
        messageDigest.update(str.getBytes());
        messageDigest.update(str.getBytes());
        System.out.println(bytes2hex(messageDigest.digest()));

        MsgDigest MsgDigest = new SHA3_384();
        MsgDigest.update(str.getBytes());
        MsgDigest.update(str.getBytes());
        System.out.println(MsgDigest);
        MsgDigest.reset();
        MsgDigest.update((str + str).getBytes());
        System.out.println(bytes2hex(MsgDigest.digest()));
    }

    @Test
    public void testSHA3_512() throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA3-512");
        messageDigest.update(str.getBytes());
        messageDigest.update(str.getBytes());
        System.out.println(bytes2hex(messageDigest.digest()));

        MsgDigest MsgDigest = new SHA3_512();
        MsgDigest.update(str.getBytes());
        MsgDigest.update(str.getBytes());
        System.out.println(MsgDigest);
        MsgDigest.reset();
        MsgDigest.update((str + str).getBytes());
        System.out.println(bytes2hex(MsgDigest.digest()));
    }
}
