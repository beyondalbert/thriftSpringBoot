package com.albert;

import com.albert.thrift.TCalculatorService;
import com.albert.thrift.TDivisionByZeroException;
import com.albert.thrift.TOperation;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ThriftSpringBootApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class ThriftSpringBootApplicationTests {

	@Test
	public void contextLoads() {
	}

	protected TCalculatorService.Client client;

	@Before
	public void setUp() throws Exception {
		TTransport transport = new TFramedTransport(new TSocket("localhost", 6666));
		TProtocol protocol = new TCompactProtocol(transport);
		transport.open();

		client = new TCalculatorService.Client(protocol);
	}

	@Test
	public void testAdd() throws Exception {
		assertEquals(5, client.calculate(2, 3, TOperation.ADD));
	}

    @Test
    public void testSubtract() throws Exception {
        assertEquals(3, client.calculate(5, 2, TOperation.SUBTRACT));
    }

    @Test
    public void testMultiply() throws Exception {
        assertEquals(10, client.calculate(5, 2, TOperation.MULTIPLY));
    }

    @Test
    public void testDivide() throws Exception {
        assertEquals(2, client.calculate(10, 5, TOperation.DIVIDE));
    }

    @Test(expected = TDivisionByZeroException.class)
    public void testDivisionByZero() throws Exception {
        client.calculate(10, 0, TOperation.DIVIDE);
    }
}
