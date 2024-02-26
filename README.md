## License授权cli工具

本项目目的：使用类似SSL证书许可的方式，防止客户欠费。
如项目试用期3天，三天到期后，项目报错证书过期。续费后，生成新的证书安装即可，不需要重新部署项目。

默认密码：1year2024fafafa29

项目使用者：部署实施人员

配套相关：

1. oshi获取硬件信息
2. true-license创建和验证证书基础库
3. 业务项目中验证证书 <如项目启动/用户登录/web拦截器/jpa拦截器等的时候验证证书>
````
open class OauthAppStartupEventListener: ApplicationEventListener<ServerStartupEvent> {


    override fun onApplicationEvent(event: ServerStartupEvent?) {
        try {
            LicenseHelper.verifyLicense()
        }catch (e:Exception){
            println("[证书]许可已到期或不正确,请向服务商获取新证书。")
            exitProcess(-1)
        }
    }



}
````
指令操作：

服务端环境：
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
生成授权证书:
java -jar .\license-1.0.0-all.jar generator -days 365 -licenseTo wangwu

````
请输入密码: 

----证书创建成功----
path: ./license.lic
````
安装证书<错误>：
java -jar .\license-1.0.0-all.jar install -path ./license.lic1
````
----证书安装失败----
.\license.lic1 (系统找不到指定的文件。)
````
安装证书：
java -jar .\license-1.0.0-all.jar install -path ./license.lic
````
----证书安装成功----
````
加载证书：
java -jar .\license-1.0.0-all.jar load 
````
授权用户：仅授权wangwu使用
过期时间：Tue Jan 28 23:41:47 CST 2025
````

验证证书：
java -jar .\license-1.0.0-all.jar verify

````
----证书校验成功----
````


