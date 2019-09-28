package org.usfirst.frc100.Team100Robot;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

import com.google.gson.Gson;

public class Server implements Runnable {
    private DatagramSocket server;
    private Gson json = new Gson();
    private byte[] buffer = new byte[1024];

    Server(String host, int port) throws IOException {
        InetAddress addr = InetAddress.getByName(host);
        server = new DatagramSocket(port, addr);
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                server.receive(packet);

                VisionTarget[] targets = json.fromJson(new String(packet.getData(), 0, packet.getLength()), VisionTarget[].class);

                Robot.targets.clear();
                Robot.targets.addAll(Arrays.asList(targets));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
