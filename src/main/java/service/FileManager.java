package service;

import entity.TransportDriver;
import entity.transports.AbstractTransport;

import java.io.*;
import java.util.ArrayList;

public class FileManager {
    private static final String FILES_DRIVERS_PATH = "files/drivers.txt";
    private static final String FILES_CARS_PATH = "files/cars.txt";
    private static final String FILES_BUSES_PATH = "files/buses.txt";
    private static final String FILES_TRUCKS_PATH = "files/trucks.txt";
    private static final String FILES_ALL_TRANSPORTS_PATH = "files/alltransports.txt";

    public static void createFile(FileNames fileName) {
        File file;
        switch (fileName) {
            case DRIVERS:
                file = new File(FILES_DRIVERS_PATH);
                break;
            case CARS:
                file = new File(FILES_CARS_PATH);
                break;
            case BUSES:
                file = new File(FILES_BUSES_PATH);
                break;
            case TRUCKS:
                file = new File(FILES_TRUCKS_PATH);
                break;
            default:
                file = new File(FILES_ALL_TRANSPORTS_PATH);
                break;
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteFile(FileNames fileName) {
        File file;
        switch (fileName) {
            case DRIVERS:
                file = new File(FILES_DRIVERS_PATH);
                break;
            case CARS:
                file = new File(FILES_CARS_PATH);
                break;
            case BUSES:
                file = new File(FILES_BUSES_PATH);
                break;
            case TRUCKS:
                file = new File(FILES_TRUCKS_PATH);
                break;
            default:
                file = new File(FILES_ALL_TRANSPORTS_PATH);
                break;
        }
        file.delete();
    }

    public static void writeDriversToFile(ArrayList<TransportDriver> list) {
        try {
            FileWriter fw = new FileWriter(FILES_DRIVERS_PATH);
            for (int i = 0; i < list.size(); i++) {
                fw.write(list.get(i).toOutputInFile());
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeTransportsToFile(FileNames fileName, ArrayList<? extends AbstractTransport> list) {
        try {
            FileWriter fw;
            switch (fileName) {
                case CARS:
                    fw = new FileWriter(FILES_CARS_PATH);
                    break;
                case BUSES:
                    fw = new FileWriter(FILES_BUSES_PATH);
                    break;
                case TRUCKS:
                    fw = new FileWriter(FILES_TRUCKS_PATH);
                    break;
                default:
                    fw = new FileWriter(FILES_ALL_TRANSPORTS_PATH);
                    break;
            }
            for (int i = 0; i < list.size(); i++) {
                fw.write(list.get(i).toOutputInFile());
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFromFile(FileNames fileName) {
        String data = "";
        try {
            FileReader fr;
            switch (fileName) {
                case DRIVERS:
                    fr = new FileReader(FILES_DRIVERS_PATH);
                    break;
                case CARS:
                    fr = new FileReader(FILES_CARS_PATH);
                    break;
                case BUSES:
                    fr = new FileReader(FILES_BUSES_PATH);
                    break;
                case TRUCKS:
                    fr = new FileReader(FILES_TRUCKS_PATH);
                    break;
                default:
                    fr = new FileReader(FILES_ALL_TRANSPORTS_PATH);
                    break;
            }
            LineNumberReader lnr = new LineNumberReader(fr);
            String s;
            while ((s = lnr.readLine()) != null) {
                data += s + " ";
            }
            lnr.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
