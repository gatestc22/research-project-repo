
import javax.sound.midi.*;
import javax.sound.midi.spi.MidiDeviceProvider;

import java.io.File;
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
        Sequencer seqr;
        Scanner sc;
        MidiDevice.Info[] infoArray = MidiSystem.getMidiDeviceInfo(); // array of all current midi device info

        // find MIDI input device
        for (int i = 0; i < infoArray.length; i++) {
            deviceIn = MidiSystem.getMidiDevice(infoArray[i]);
            if (deviceIn.toString().contains("MidiInDevice")) {
                System.out.println(deviceIn.toString());
                //deviceIn.open();
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
        seqr = MidiSystem.getSequencer();
//        Transmitter transmitter;
//        Receiver receiver;

        // TODO: while loop
        while (deviceIn.isOpen() && deviceOut.isOpen()) {

            midiIn.setReceiver(midiOut);
            //System.out.println(midiOut);
            seqr.addMetaEventListener(new MetaEventListener() {
                @Override
                public void meta(MetaMessage meta) {
                    System.out.println("Input Received!");
                }
            });
            seqr.addControllerEventListener(
                    new ControllerEventListener() {
                        public void controlChange(ShortMessage event) {
                            System.out.println(event.getData1());
                            System.out.println(event.getData2());
                        }
                    },
                    getMidiFileTypes(seqr.getSequence())
            );
//
//            // Open a connection to your input device
//            deviceIn.open();
//// Open a connection to the default sequencer (as specified by MidiSystem)
//            seqr.open();
//// Get the transmitter class from your input device
//            transmitter = deviceIn.getTransmitter();
//// Get the receiver class from your sequencer
//            receiver = seqr.getReceiver();
//// Bind the transmitter to the receiver so the receiver gets input from the transmitter
//            transmitter.setReceiver(receiver);
//
//// And of course a track to record the input on
//            Track currentTrack = seqr.getSequence().createTrack();
//// Do some sequencer settings
//            seqr.setSequence(seqr.getSequence());
//            seqr.setTickPosition(0);
//            seqr.recordEnable(currentTrack, -1);
//// And start recording
//            seqr.startRecording();
//
//            // Stop recording
//            if(seqr.isRecording())
//            {
//                // Tell sequencer to stop recording
//                //sequencer.stopRecording();
//
//                // Retrieve the sequence containing the stuff you played on the MIDI instrument
//                Sequence tmp = seqr.getSequence();
//
//                // Save to file
//                MidiSystem.write(tmp, 0, new File("MyMidiFile.mid"));
//            }
//
        }

        deviceIn.close();
        deviceOut.close();

    }

}