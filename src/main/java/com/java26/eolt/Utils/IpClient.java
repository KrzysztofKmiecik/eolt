package com.java26.eolt.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class IpClient {
    public static void main(String[] args) {


        IpClient ipClient = new IpClient();
        //   ipClient.echoIP();
        String str = ipClient.sendAndReceiveIPMessage("10.235.241.235", 24065, "Hello");
        System.out.println(str);
    }

    public String sendAndReceiveIPMessage(String ipAdress, Integer ipPort, String message) {

        String str = "";
        try (
                Socket mySocket = new Socket(ipAdress, ipPort);
                PrintWriter printWriter = new PrintWriter(mySocket.getOutputStream());
                BufferedReader in = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));

        ) {

            printWriter.println(message);
            printWriter.flush();
            str = in.readLine();

            in.close();
            printWriter.close();
            mySocket.close();

        } catch(UnknownHostException e)

    {
        System.err.println(e);

    } catch(Exception e)

    {
        System.err.println(e);

    }finally {

        }

        return str;

}

    public void echoIP() {

          /* if (args.length != 2) {
            System.err.println(
                    "Usage: java EchoClient <host name> <port number>");
            System.exit(1);
        }*/

        String hostName = "10.235.241.235";//args[0];
        int portNumber = 24065;//Integer.parseInt(args[1]);


        try (
                Socket echoSocket = new Socket(hostName, portNumber);
                PrintWriter out =
                        new PrintWriter(echoSocket.getOutputStream(), true);
                BufferedReader in =
                        new BufferedReader(
                                new InputStreamReader(echoSocket.getInputStream()));
                BufferedReader stdIn =
                        new BufferedReader(
                                new InputStreamReader(System.in))
        ) {
            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                System.out.println("echo: " + in.readLine());
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    hostName);
            System.exit(1);
        }


    }
}




