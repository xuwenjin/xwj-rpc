# xwj-rpc
手写一个简易版rpc框架

参考地址：https://blog.csdn.net/liman65727/article/details/99690754

服务端技术点：
1、ServerSocket：接收客户端请求
2、反射：获取客户端请求参数，请求方法

客户端技术点：
1、Socket：向服务端发送请求
2、JDK动态代理：获取请求参数，请求方法，调用send方法，返回响应结果

注意点：请求的对象，一定要记得序列化
