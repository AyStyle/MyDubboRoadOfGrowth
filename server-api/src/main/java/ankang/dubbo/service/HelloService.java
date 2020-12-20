package ankang.dubbo.service;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-12-14
 */
public interface HelloService {

    String sayHello(String name);

    String sayHello(String name , int timeWait);
}
