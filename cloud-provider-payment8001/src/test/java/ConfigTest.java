import com.gw.springcloud.PaymentMain8001;
import com.gw.springcloud.entities.Payment;
import com.gw.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PaymentMain8001.class)
@Slf4j
public class ConfigTest {

    @Resource
    private PaymentService paymentService;

    @Test
    public void test01() {
        Payment paymentById = paymentService.getPaymentById(1L);
        System.out.println(paymentById);
    }
}
