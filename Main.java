import MIDIStuff.MidiSynth;

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

        try {
//            System.out.print("Print devices? [y/n]: ");
//            String choice = choose.nextLine();
//            if (choice.equals("y")) {
//                choose.nextLine();
//            }

//            MidiDevice.Info[] info =
//                    MidiSystem.getMidiDeviceInfo();
//            trans = getTransmitter();
//            rec = getReceiver();
//            MidiDevice device = MidiSystem.getMidiDevice(info[4]);

            // Obtain information about all the installed synthesizers.
            MidiDevice.Info[] info = MidiSystem.getMidiDeviceInfo();
            ArrayList<MidiDevice> deviceList = new ArrayList<>();
            for (int i = 0; i < info.length; i++) {
                try {
                    MidiSystem.getMidiDevice(info[i]).open();
                    deviceList.add(MidiSystem.getMidiDevice(info[i]));
                    System.out.println(MidiSystem.getMidiDevice(info[i]));
                } catch (MidiUnavailableException e) {
                    System.out.println("no midi :(");
                }
            }


//            device.getTransmitter();
//// Get the transmitter class from your input device
//            transmitter = inputDevice.getTransmitter();
//// Get the receiver class from your sequencer
//            receiver = sequencer.getReceiver();
//// Bind the transmitter to the receiver so the receiver gets input from the transmitter
//            transmitter.setReceiver(receiver);
//
//// Create a new sequence
//            Sequence seq = new Sequence(Sequence.PPQ, 24);
//// And of course a track to record the input on
//            Track currentTrack = seq.createTrack();
//// Do some sequencer settings
//            sequencer.setSequence(seq);
//            sequencer.setTickPosition(0);
//            sequencer.recordEnable(currentTrack, -1);
//// And start recording
//            sequencer.startRecording();


//
//            System.out.println(trans);
//            System.out.println(rec);
//
//             trans.setReceiver(device.getReceiver());
//
//            System.out.println(trans.getReceiver());
//
//            trans.close();
//            rec.close();
        } finally {

        }

    }

//    static void getInfo() throws MidiUnavailableException {
//        MidiDevice.Info[] info =
//                MidiSystem.getMidiDeviceInfo();
//
//        for (int i=0; i < info.length; i++) {
//            System.out.println(i + ") " + info[i]);
//            System.out.println("Name: " + info[i].getName());
//            System.out.println("Description: " +
//                    info[i].getDescription());
//
//            MidiDevice device =
//                    MidiSystem.getMidiDevice(info[i]);
//            System.out.println("Device: " + device);
//        }
//    }
}