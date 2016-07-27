package com.albert;

import com.albert.thrift.TCalculatorService;
import com.albert.thriftHandler.CalculatorServiceHandler;
import org.apache.log4j.Logger;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TServerSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by albert on 16-7-26.
 */

@Component
public class ThriftServer {
    private static Logger logger = Logger.getLogger(ThriftServer.class);

    @Value("${thrift.server.port}")
    private int thriftServerPort;

    @Autowired
    CalculatorServiceHandler calculatorServiceHandler;

    public void startServer() {
        try {
            TProcessor tProcessor = new TCalculatorService.Processor<>(calculatorServiceHandler);
            TServerSocket tServerSocket = new TServerSocket(thriftServerPort);
            TThreadPoolServer.Args tpsArgs = new TThreadPoolServer.Args(tServerSocket);

            tpsArgs.processor(tProcessor);
            tpsArgs.transportFactory(new TFramedTransport.Factory());
            tpsArgs.protocolFactory(new TCompactProtocol.Factory());

            TServer server = new TThreadPoolServer(tpsArgs);
            logger.info("start thrift server at: " + thriftServerPort);
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
