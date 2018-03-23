package test;

import cn.MsgDigest;
import cn.util.ConvertUtils;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;


public class Demo implements ConvertUtils{
    @Test
    public void test01() {
        //待测字符串
        String str = "·1234567890-=【】、；‘，。、~~!@#$%^&*()_+P{}ASHHJ前为额日条与uint牌哦くぇちゅいおぱ:";
        //JDK原生方法
        java.util.zip.CRC32 crc32 = new java.util.zip.CRC32();
        crc32.update(str.getBytes());
        //支持分段计算
        crc32.update(str.getBytes());
        //自定义byte数组转String方法，long转byte数组方法
        System.out.println(bytes2hex(long2bytes(crc32.getValue())));

        MsgDigest MsgDigest = new cn.crc.impl.CRC32();
        MsgDigest.update(str.getBytes());
        //支持分段计算
        MsgDigest.update(str.getBytes());
        //重写toString方法，直接输出十六进制字符串
        System.out.println(MsgDigest);
        //重置状态
        MsgDigest.reset();
        MsgDigest.update((str + str).getBytes());
        //获取结果的字节数组，然后进行转换
        System.out.println(bytes2hex(MsgDigest.digest()));
    }
}
