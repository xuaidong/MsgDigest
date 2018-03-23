# MsgDigest
支持MD2、MD4、MD5、CRC32、CRC32C、SHA-1、SHA-224、SHA-256、SHA-384、SHA-512、SHA-512/224、SHA-512/256、SHA3-224、SHA3-256、SHA3-384、SHA3-512摘要算法，纯JAVA代码，基本数据类型

调用方法：
```
//创建对象
MsgDigest MsgDigest = new MD5();
//更新摘要
MsgDigest.update(str.getBytes());
//支持分段计算，接着上一次结果继续更新
MsgDigest.update(str.getBytes());
//重写toString方法，直接输出十六进制字符串
System.out.println(MsgDigest);
//重置状态
MsgDigest.reset();
//获取结果的字节数组，然后进行转换
MsgDigest.update((str + str).getBytes());
System.out.println(bytes2hex(MsgDigest.digest()));
```
