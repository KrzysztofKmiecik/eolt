package com.java26.eolt.service;

import com.java26.eolt.Utils.IpClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class FISservice {
    public void sendAndReceiveIPMessage(String msg) {
        final IpClient ipClient = new IpClient();
        final String actual = ipClient.sendAndReceiveIPMessage("10.235.241.235", 24364, "HandleGETSTATIONLIST|");
        log.info("my message is = " + msg + "    my sesponse is = " + actual);
        System.out.println(msg + actual);
    }
}
