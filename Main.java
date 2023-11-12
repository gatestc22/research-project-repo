
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
                deviceIn.open();
                System.out.println(deviceIn.toString() + " opened.");
                if (deviceIn.getMaxTransmitters() != 0) {
                    midiIn  = deviceIn.getTransmitter();
                }
            }
        }

        // find MIDI output device
        for (int i = 0; i < infoArray.length; i++) {
            deviceOut = MidiSystem.getMidiDevice(infoArray[i]);
            // TODO: find better way to specify output device
            if (deviceOut.getDeviceInfo().getName().contains("Microsoft GS Wavetable Synth")) {
                // TODO: check if needs to open / delete both opens bc unnecessary (and closes?)
                if (deviceOut.getMaxReceivers() != 0) {
                    midiOut  = deviceOut.getReceiver();
                }
            }
        }

        seqr = MidiSystem.getSequencer();
        seqr.open();
        // transmitter: midiIn
        // receiver: midiOut
        Sequence seq = new Sequence(Sequence.PPQ, 24);
        Track currentTrack = seq.createTrack();
        seqr.setSequence(seq);
        seqr.setTickPosition(0);
        seqr.recordEnable(currentTrack, -1);
        seqr.startRecording();

        midiIn.setReceiver(midiOut);
        while (deviceIn.isOpen()) {
        }



//        // TODO: while loop
//        while (deviceIn.isOpen() && deviceOut.isOpen()) {
//
//            seqr.addMetaEventListener(new MetaEventListener() {
//                @Override
//                public void meta(MetaMessage meta) {
//                    System.out.println(meta.getData());
//                }
//            });
//            seqr.addControllerEventListener(
//                    new ControllerEventListener() {
//                        public void controlChange(ShortMessage event) {
//                            System.out.println(event.getData1());
//                            System.out.println(event.getData2());
//                        }
//                    },
//                    getMidiFileTypes(seqr.getSequence())
//            );
//
//        }

        deviceIn.close();
        deviceOut.close();

    }

}