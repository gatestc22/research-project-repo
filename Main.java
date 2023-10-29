
import javax.sound.midi.*;
import javax.sound.midi.spi.MidiDeviceProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static javax.sound.midi.MidiSystem.*;

public class Main {

    public static void main(String[] args) throws Exception {

        // new code
        MidiDevice device;

        Transmitter trans;
        Receiver rec;
        Scanner sc = new Scanner(System.in);

        Sequencer seq;
        Synthesizer syn;
        int numTrans;
        int numRec;

        try {

            // obtain information about all recognizable midi devices
            MidiDevice.Info[] infoArray = MidiSystem.getMidiDeviceInfo();
            ArrayList<MidiDevice> deviceList = new ArrayList<>();
            for (int i = 0; i < infoArray.length; i++) {
                // initialize device variable with info at position i in infoArray
                device = MidiSystem.getMidiDevice(infoArray[i]);
                deviceList.add(device);
                System.out.println("Device: " + device.getDeviceInfo());
            }

            // get default sequencer and synthesizer
            seq = MidiSystem.getSequencer();
            syn = MidiSystem.getSynthesizer();

            if (seq != null) System.out.println("Seq: " + seq.getDeviceInfo());
            numTrans = seq.getTransmitters().size();
            numRec = seq.getReceivers().size();
            System.out.println("Num Trans: " + numTrans);
            System.out.println("Num Rec: " + numRec);

            if (syn != null) System.out.println("Syn: " + syn.getDeviceInfo());
            numTrans = syn.getTransmitters().size();
            numRec = syn.getReceivers().size();
            System.out.println("Num Trans: " + numTrans);
            System.out.println("Num Rec: " + numRec);

            for (int i = 0; i < deviceList.size(); i++) {
                // TODO: obtain device at position i from deviceList
                System.out.println();
            }

//
//                //if any transmitting devices were found
//                if(deviceList.size() > 0) {
//                    //for each device
//                    for(int i = 0; i < deviceList.size(); i++) {
//                        try {
//                            //get all transmitters
//                            List<Transmitter> transmitters = deviceList.get(i).getTransmitters();
//                            //and for each transmitter
//                            for(int j = 0; j<transmitters.size();j++) {
//                                //create a new receiver
//                                transmitters.get(i).setReceiver(
//                                        deviceList.get(i).getReceiver()
//                                );
//                            }
//                            //open each device
//                            deviceList.get(i).open();
//                            System.out.println(deviceList.get(i).getDeviceInfo()+" Was Opened");
//                        } catch (MidiUnavailableException e) {}
//                    }
//                }
        } finally {

        }

    }

}