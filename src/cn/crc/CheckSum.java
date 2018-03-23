package cn.crc;

import cn.MsgDigest;
import cn.util.ConvertUtils;

public abstract class CheckSum extends MsgDigest
        implements ConvertUtils {

    public CheckSum(String algorithm) {
        super(algorithm);
    }

    public abstract void reset();

    @Override
    public void update(byte[] b) {
        update(b, 0, b.length);
    }

    public abstract void update(int b);

    public abstract void update(byte[] b, int off, int len);

    public abstract long getValue();

    @Override
    public String toString() {
        return bytes2hex(digest());
    }

    @Override
    public byte[] digest() {
        return long2bytes(getValue());
    }
}
