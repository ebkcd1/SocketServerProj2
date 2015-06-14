package socketserverprj2;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketServerPrj2 {

    public static void main(String[] args) throws IOException, InterruptedException {
        //ServerSocket serverSocket = null;
        System.out.println("Waiting for connection.....");
        try {
            ServerSocket serverSocket = new ServerSocket(6544);
            while (true) {
                new ThreadedServer(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port: 6544");

        }

    }//end main 

}//end ServerSocket class

class ThreadedServer extends Thread {

    private Socket clientSocket = null;

    public ThreadedServer(Socket cs) {
        super("ThreadedServer");
        clientSocket = cs;
    }

    @Override
    public void run() {

        try {

            PrintWriter outStream = null;
            BufferedReader inStream = null;
            int selection = 0;

            System.out.println("Connection successful");
            try {
                outStream = new PrintWriter(clientSocket.getOutputStream(), true);
                inStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                BufferedReader commandOutput;
                outStream.checkError();

                System.out.println("Waiting for input...");
                String userInput = inStream.readLine();

                try {
                    selection = Integer.parseInt(userInput);
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                    System.out.println("Input needs to be a number!");
                }
                System.out.println(selection);

                switch (selection) {
                    //cmd /c date is the command for Windows. This will need to be changed for linux
                    //for linux it might be just date
                    // /c carries out the command execution
                    case 1: {
                        System.out.println("Executing 'date'");
                        Process process = Runtime.getRuntime().exec("date");
                        commandOutput = new BufferedReader(new InputStreamReader(process.getInputStream()));

                        String line;
                        while ((line = commandOutput.readLine()) != null) {
                            outStream.println(line);
                        }
                        System.out.println("bye");
                        outStream.println("bye");
                        break;
                    }

                    //uptime case
                    case 2: {
                        System.out.println("Executing 'uptime'");
                        Process process = Runtime.getRuntime().exec("uptime");
                        commandOutput = new BufferedReader(new InputStreamReader(process.getInputStream()));

                        String line;
                        while ((line = commandOutput.readLine()) != null) {
                            System.out.println(line);
                            outStream.println(line);
                        }

                        outStream.println("bye");
                        break;
                    }
                    //memory use case
                    case 3: {
                        System.out.println("Executing 'free'");
                        Process process = Runtime.getRuntime().exec("free");
                        commandOutput = new BufferedReader(new InputStreamReader(process.getInputStream()));

                        String line;

                        while ((line = commandOutput.readLine()) != null) {
                            System.out.println(line);
                            outStream.println(line);
                        }
                        process.destroy();
                        inStream.close();
                        outStream.println("bye");
                        break;
                    }
                    // netstat request case
                    case 4: {
                        System.out.println("Executing 'netstat'");
                        Process process = Runtime.getRuntime().exec("netstat");
                        commandOutput = new BufferedReader(new InputStreamReader(process.getInputStream()));
                        String line;
                        while ((line = commandOutput.readLine()) != null) {
                            System.out.println(line);
                            outStream.println(line);
                        }
                        process.destroy();
                        inStream.close();
                        outStream.println("bye");
                        break;
                    }
                    //list current processes case
                    case 5: {
                        System.out.println("Executing 'ps -c'");
                        Process process = Runtime.getRuntime().exec("ps -c");
                        commandOutput = new BufferedReader(new InputStreamReader(process.getInputStream()));
                        String line;
                        while ((line = commandOutput.readLine()) != null) {
                            System.out.println(line);
                            outStream.println(line);
                        }
                        process.destroy();
                        inStream.close();
                        outStream.println("bye");
                        break;
                    }
                    //list current user case
                    case 6: {
                        System.out.println("Executing 'who'");
                        Process process = Runtime.getRuntime().exec("who");
                        commandOutput = new BufferedReader(new InputStreamReader(process.getInputStream()));

                        String line;
                        while ((line = commandOutput.readLine()) != null) {
                            System.out.println(line);
                            outStream.println(line);
                        }
                        process.destroy();
                        inStream.close();
                        outStream.println("bye");
                        break;
                    }

                    default: {
                        System.out.println(selection);
                        outStream.println("Please enter a number between 1 and 7");
                        selection = 1;
                        break;
                    }

                } //end switch

                //outStream.println("Goodbye!");
            } catch (Exception e) {
                e.printStackTrace();
            }

            outStream.close();
            inStream.close();
            clientSocket.close();

        }//end run()
        catch (IOException ex) {
            Logger.getLogger(ThreadedServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}// end ThreadedServer

