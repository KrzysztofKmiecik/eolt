package com.java26.eolt.service;

import com.java26.eolt.Utils.IpClient;
import com.java26.eolt.exception.FISVariantNotFoundExeption;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * FIS (Factory Information System)
 * Server   IP : 10.235.241.235  Port : 24364
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FISservice {

    public void sendAndReceiveIPMessage(String msg) {
        final IpClient ipClient = new IpClient();
        //  final String actual = ipClient.sendAndReceiveIPMessage("10.235.241.235", 24364, "HandleGETSTATIONLIST|");
        final String receiveIPMessage = ipClient.sendAndReceiveIPMessage("10.235.241.235", 24364, msg);
        if(receiveIPMessage.contains("FAIL")){
            throw new FISVariantNotFoundExeption(receiveIPMessage);
        }
        log.info("my message is = " + msg + "--->>> FIS response is = " + receiveIPMessage);
        System.out.println(msg + receiveIPMessage);
    }


    /**
     * FIS Command format "ADDVARIANT|variant=28636935|station=VIDEO_EOLT_F|status=PASS"
     */
    public String createADDVARIANT(final String stationNetName, final String variant, final String status) {
           return "ADDVARIANT|variant=" + variant.trim().toUpperCase()
                + "|station=" + stationNetName.trim().toUpperCase()
                + "|status=" + status.trim().toUpperCase();
    }
}
