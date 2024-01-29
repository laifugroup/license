## License授权客户端

本项目目的：使用证书的方式，防止客户欠费。

指令
java -jar .\license-1.0.0-all.jar env

````
{
  "cpu" : "BFEBFBFF000506E3",
  "ip" : ",192.168.1.155,172.24.176.1",
  "mac" : "10:98:36:aa:f5:4b,10:98:36:aa:f5:4c,00:15:5d:08:8f:76",
  "mainBoard" : "4C4C4544-0052-4810-8050-C3C04F5A4732",
}
---- 请把以上JSON提供给服务商 ----

````
java -jar .\license-1.0.0-all.jar generator -days 365 -licenseTo wangwu

````
请输入密码: 

----证书创建成功----
path: ./license.lic
````

java -jar .\license-1.0.0-all.jar install -path ./license.lic1
````
----证书安装失败----
.\license.lic1 (系统找不到指定的文件。)
````

java -jar .\license-1.0.0-all.jar install -path ./license.lic
````
----证书安装成功----
````

java -jar .\license-1.0.0-all.jar load 
````
授权用户：仅授权wangwu使用
过期时间：Tue Jan 28 23:41:47 CST 2025
````


java -jar .\license-1.0.0-all.jar verify

````
----证书校验成功----
````


