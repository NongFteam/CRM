/**
 * 
 */
package test.service;

import java.util.List;

import com.neuedu.crm.service.impl.UserServiceImpl;
import com.neuedu.crm.utils.Md5Util;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.neuedu.crm.pojo.User;
import com.neuedu.crm.pojo.UserExample;
import com.neuedu.crm.service.IUserService;

/**
 * @author wanghaoyu
 *
 */
public class TestUserService {

    /*protected ApplicationContext context;

    protected IUserService userService;
    
    private Logger logger = LoggerFactory.getLogger(TestUserService.class);
    
    @Test
    public void init(){
        try {
            String configLocation = "spring/applicationContext.xml";
            context = new ClassPathXmlApplicationContext(configLocation);
            logger.error("0000000000000000"+context);
            userService = context.getBean(IUserService.class);
            logger.error("+++++++11111111111111"+userService);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //@Test
    public void testFindByExample(){
        UserExample userExample = new UserExample();
        List<User> users= userService.findByExample(userExample);
        if(users.size() > 0) {
            for (User user : users) {
                logger.info(user.toString());
            }
        }else {
            logger.info("不存在该用户");
        }
    }*/

    /**
     * 需要手动去数据库添加数据，自动化方式无法注入所需要的bean，暂不知道原因
     * */
    
    @Test
    public void testAddUser(){

        User user = new User();
        user.setAccount("admin");
        user.setPassword("123456");
        user.setRealName("admin");

        String salt1 = user.getAccount();
        String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
        user.setSalt(salt2);
        //使用Md5Util进行Md5盐值加密
        String encodedPassword = Md5Util.encrypt(user.getPassword(), salt1 + salt2);
        //设置加密后的用户密码
        user.setPassword(encodedPassword);

        System.out.println(salt2+salt1);   //数据库中的salte字段
        System.out.println(encodedPassword);   //加密过后的密码
    }

    /*
    //@Test
    public void testeditUser(){
        User user = new User();
        user.setId(19);
        user.setAccount("wang");
        if( userService.edit(user) == true) {
            logger.info("添加成功");
        }
        else {
            logger.info("添加失败");
        }
    }
    
    //@Test
    public void testdelUser(){
        User user = new User();
        if( userService.deleteById(19) == true) {
            logger.info("删除成功");
        }
        else {
            logger.info("删除失败");
        }
    }*/
    
}
