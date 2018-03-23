package cn.hash.sha;

import cn.hash.HASH;

public abstract class SHA5 extends HASH {

    private long[] W;

    private long[] state;

    private final long[] initialHashes;

    private static final int ITERATION = 80;

    private static final long[] ROUND_CONSTS = {
            0x428A2F98D728AE22L, 0x7137449123EF65CDL, 0xB5C0FBCFEC4D3B2FL,
            0xE9B5DBA58189DBBCL, 0x3956C25BF348B538L, 0x59F111F1B605D019L,
            0x923F82A4AF194F9BL, 0xAB1C5ED5DA6D8118L, 0xD807AA98A3030242L,
            0x12835B0145706FBEL, 0x243185BE4EE4B28CL, 0x550C7DC3D5FFB4E2L,
            0x72BE5D74F27B896FL, 0x80DEB1FE3B1696B1L, 0x9BDC06A725C71235L,
            0xC19BF174CF692694L, 0xE49B69C19EF14AD2L, 0xEFBE4786384F25E3L,
            0x0FC19DC68B8CD5B5L, 0x240CA1CC77AC9C65L, 0x2DE92C6F592B0275L,
            0x4A7484AA6EA6E483L, 0x5CB0A9DCBD41FBD4L, 0x76F988DA831153B5L,
            0x983E5152EE66DFABL, 0xA831C66D2DB43210L, 0xB00327C898FB213FL,
            0xBF597FC7BEEF0EE4L, 0xC6E00BF33DA88FC2L, 0xD5A79147930AA725L,
            0x06CA6351E003826FL, 0x142929670A0E6E70L, 0x27B70A8546D22FFCL,
            0x2E1B21385C26C926L, 0x4D2C6DFC5AC42AEDL, 0x53380D139D95B3DFL,
            0x650A73548BAF63DEL, 0x766A0ABB3C77B2A8L, 0x81C2C92E47EDAEE6L,
            0x92722C851482353BL, 0xA2BFE8A14CF10364L, 0xA81A664BBC423001L,
            0xC24B8B70D0F89791L, 0xC76C51A30654BE30L, 0xD192E819D6EF5218L,
            0xD69906245565A910L, 0xF40E35855771202AL, 0x106AA07032BBD1B8L,
            0x19A4C116B8D2D0C8L, 0x1E376C085141AB53L, 0x2748774CDF8EEB99L,
            0x34B0BCB5E19B48A8L, 0x391C0CB3C5C95A63L, 0x4ED8AA4AE3418ACBL,
            0x5B9CCA4F7763E373L, 0x682E6FF3D6B2B8A3L, 0x748F82EE5DEFB2FCL,
            0x78A5636F43172F60L, 0x84C87814A1F0AB72L, 0x8CC702081A6439ECL,
            0x90BEFFFA23631E28L, 0xA4506CEBDE82BDE9L, 0xBEF9A3F7B2C67915L,
            0xC67178F2E372532BL, 0xCA273ECEEA26619CL, 0xD186B8C721C0C207L,
            0xEADA7DD6CDE0EB1EL, 0xF57D4F7FEE6ED178L, 0x06F067AA72176FBAL,
            0x0A637DC5A2C898A6L, 0x113F9804BEF90DAEL, 0x1B710B35131C471BL,
            0x28DB77F523047D84L, 0x32CAAB7B40C72493L, 0x3C9EBE0A15C9BEBCL,
            0x431D67C49C100D4CL, 0x4CC5D4BECB3E42B6L, 0x597F299CFC657E2AL,
            0x5FCB6FAB3AD6FAECL, 0x6C44198C4A475817L
    };

    public SHA5(String name, int digestLength, long[] initialHashes) {
        super(name, digestLength, 128);
        this.initialHashes = initialHashes;
        state = new long[8];
        W = new long[80];
        implReset();
    }

    @Override
    public void implReset() {
        arrayCopy(initialHashes, 0, state, 0, state.length);
    }

    @Override
    public void implCompress(byte[] buf, int ofs) {
        b2iBig128(buf, ofs, W);
        for (int t = 16; t < ITERATION; t++) {
            W[t] = lf_delta1(W[t - 2]) + W[t - 7] + lf_delta0(W[t - 15]) + W[t - 16];
        }

        long a = state[0];
        long b = state[1];
        long c = state[2];
        long d = state[3];
        long e = state[4];
        long f = state[5];
        long g = state[6];
        long h = state[7];

        for (int i = 0; i < ITERATION; i++) {
            long T1 = h + lf_sigma1(e) + lf_ch(e, f, g) + ROUND_CONSTS[i] + W[i];
            long T2 = lf_sigma0(a) + lf_maj(a, b, c);
            h = g;
            g = f;
            f = e;
            e = d + T1;
            d = c;
            c = b;
            b = a;
            a = T1 + T2;
        }
        state[0] += a;
        state[1] += b;
        state[2] += c;
        state[3] += d;
        state[4] += e;
        state[5] += f;
        state[6] += g;
        state[7] += h;
    }

    @Override
    public void implDigest(byte[] out, int ofs) {
        long bitsProcessed = bytesProcessed << 3;

        int index = (int) bytesProcessed & 0x7f;
        int padLen = (index < 112) ? (112 - index) : (240 - index);
        update(padding, 0, padLen + 8);

        i2bBig4((int) (bitsProcessed >>> 32), buffer, 120);
        i2bBig4((int) bitsProcessed, buffer, 124);
        implCompress(buffer, 0);

        int len = getDigestLength();
        if (len == 28) {
            i2bBig(state, 0, out, ofs, 24);
            i2bBig4((int) (state[3] >> 32), out, ofs + 24);
        } else {
            i2bBig(state, 0, out, ofs, len);
        }
    }

    private long lf_ch(long x, long y, long z) {
        return (x & y) ^ ((~x) & z);
    }

    private long lf_maj(long x, long y, long z) {
        return (x & y) ^ (x & z) ^ (y & z);
    }

    private long lf_R(long x, int s) {
        return (x >>> s);
    }

    private long lf_S(long x, int s) {
        return (x >>> s) | (x << (64 - s));
    }

    private long lf_sigma0(long x) {
        return lf_S(x, 28) ^ lf_S(x, 34) ^ lf_S(x, 39);
    }

    private long lf_sigma1(long x) {
        return lf_S(x, 14) ^ lf_S(x, 18) ^ lf_S(x, 41);
    }

    private long lf_delta0(long x) {
        return lf_S(x, 1) ^ lf_S(x, 8) ^ lf_R(x, 7);
    }

    private long lf_delta1(long x) {
        return lf_S(x, 19) ^ lf_S(x, 61) ^ lf_R(x, 6);
    }
}
