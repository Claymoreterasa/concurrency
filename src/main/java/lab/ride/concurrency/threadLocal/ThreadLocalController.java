package lab.ride.concurrency.threadLocal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cwz
 * @date 2018/11/20
 */
@RestController
@RequestMapping("/threadLocal")
public class ThreadLocalController {
    @RequestMapping("/test")
    public Long test(){
        System.out.println("hhhh");
        return RequestHolder.getId();
    }
}
