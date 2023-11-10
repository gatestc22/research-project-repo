
import javax.sound.midi.*;
import javax.sound.midi.spi.MidiDeviceProvider;

import java.io.InputStream;
import java.util.*;

import static javax.sound.midi.MidiSystem.*;

public class Main {

    public static void main(String[] args) throws Exception {

        // device vars must be initialized outside first for loop
        MidiDevice deviceIn = null;
        MidiDevice deviceOut = null;
        Transmitter midiIn = null;
        Receiver midiOut = null;
        Scanner sc;
        MidiDevice.Info[] infoArray = MidiSystem.getMidiDeviceInfo(); // array of all current midi device info

        // find MIDI input device
        for (int i = 0; i < infoArray.length; i++) {
            deviceIn = MidiSystem.getMidiDevice(infoArray[i]);
            if (deviceIn.toString().contains("MidiInDevice")) {
                System.out.println(deviceIn.toString());
                deviceIn.open();
                // print device info
                System.out.println("~~Device Info~~");
                System.out.println("Name: " + deviceIn.getDeviceInfo().getName());
                System.out.println("Description: " + deviceIn.getDeviceInfo().getDescription());
                if (deviceIn.getMaxTransmitters() != 0) {
                    // print device transmitter info
                    System.out.println("~~Device Transmitters~~");
                    System.out.println("Total Active: " + deviceIn.getTransmitters().size());
                    System.out.println("Default: " + deviceIn.getTransmitter());
                    midiIn  = deviceIn.getTransmitter();
                }
            }
        }

        // find MIDI output device
        for (int i = 0; i < infoArray.length; i++) {
            deviceOut = MidiSystem.getMidiDevice(infoArray[i]);
            // TODO: find better way to specify output device
            if (deviceOut.getDeviceInfo().getName().contains("Microsoft GS Wavetable Synth")) {
                System.out.println(deviceOut.toString());
                // TODO: check if needs to open / delete both opens bc unnecessary (and closes?)
                deviceOut.open();
                // print device info
                System.out.println("~~Device Info~~");
                System.out.println("Name: " + deviceOut.getDeviceInfo().getName());
                System.out.println("Description: " + deviceOut.getDeviceInfo().getDescription());
                if (deviceOut.getMaxReceivers() != 0) {
                    // print device receiver info
                    System.out.println("~~Device Receivers~~");
                    System.out.println("Total Active: " + deviceOut.getReceivers().size());
                    System.out.println("Default: " + deviceOut.getReceiver());
                    midiOut  = deviceOut.getReceiver();
                }
            }
            System.out.println();
        }

        System.out.println();

        // TODO: while loop
        while (deviceIn.isOpen() && deviceOut.isOpen()) {

            midiIn.setReceiver(midiOut);
            //System.out.println(midiOut);

//            Sequencer seqr;
//            seqr.addMetaEventListener(new MetaEventListener() {
//                @Override
//                public void meta(MetaMessage meta) {
//                    System.out.println("Input Received!");
//                }
//            });
//            seqr.addControllerEventListener(
//                    new ControllerEventListener() {
//                        public void controlChange(ShortMessage event) {
//                            System.out.println(event.getData1());
//                            System.out.println(event.getData2());
//                        }
//                    }
//            )

        }

        deviceIn.close();
        deviceOut.close();

    }

}