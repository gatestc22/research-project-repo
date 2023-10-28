
import javax.sound.midi.*;
import javax.sound.midi.spi.MidiDeviceProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static javax.sound.midi.MidiSystem.*;

public class Main {

    public static void main(String[] args) throws Exception {

        Transmitter trans;
        Receiver rec;
        Scanner sc = new Scanner(System.in);

        MidiDevice device;
        try {
            // Obtain information about all the installed synthesizers.
            MidiDevice.Info[] info = MidiSystem.getMidiDeviceInfo();
            ArrayList<MidiDevice> deviceList = new ArrayList<>();
            for (int i = 0; i < info.length; i++) {
                try {
                    device = MidiSystem.getMidiDevice(info[i]);
                    System.out.println("device: " + MidiSystem.getMidiDevice(info[i]).getDeviceInfo());
                    //does the device have any transmitters?
                    System.out.println("transmitters(" + device.getTransmitters().size() + "):" + device.getTransmitter());
                    if (device.getTransmitters().size() > 0) {
                        //if it does, add it to the device list
                        deviceList.add(device);
                    } else {
                        System.out.println("no transmitters");
                    }
                } catch (MidiUnavailableException e) {
                    System.out.println("no midi :(");
                }
            }

                //if any transmitting devices were found
                if(deviceList.size() > 0) {
                    //for each device
                    for(int i = 0; i < deviceList.size(); i++) {
                        try {
                            //get all transmitters
                            List<Transmitter> transmitters = deviceList.get(i).getTransmitters();
                            //and for each transmitter
                            for(int j = 0; j<transmitters.size();j++) {
                                //create a new receiver
                                transmitters.get(i).setReceiver(
                                        deviceList.get(i).getReceiver()
                                );
                            }
                            //open each device
                            deviceList.get(i).open();
                            System.out.println(deviceList.get(i).getDeviceInfo()+" Was Opened");
                        } catch (MidiUnavailableException e) {}
                    }
                }
        } finally {

        }

    }

    //tried to write my own class. I thought the send method handles an MidiEvents sent to it
//    public static class MidiInputReceiver implements Receiver {
//        public String name;
//        public MidiInputReceiver(String name) {
//            this.name = name;
//        }
//        public void send(MidiMessage msg, long timeStamp) {
//            System.out.println("midi received");
//        }
//        public void close() {}
//    }

}