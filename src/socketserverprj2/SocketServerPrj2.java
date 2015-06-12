package socketserverprj2;

import java.net.*;
import java.io.*;

public class SocketServerPrj2 {

    
   public static void main(String[] args) throws IOException, InterruptedException {
        //ServerSocket serverSocket = null;
        System.out.println("Waiting for connection.....");
            try{ServerSocket serverSocket = new ServerSocket(6544);
            while(true){
               new ThreadedServer(serverSocket.accept()).start();
                
            }
            }catch (IOException e) {
	            System.err.println("Could not listen on port: 6544");
	            
	        }

       
        
    }//end main 
    
}//end ServerSocket class

class ThreadedServer extends Thread{
    private Socket clientSocket = null;
    
    public ThreadedServer(Socket cs){
        super("ThreadedServer");
        clientSocket = cs;
    }
    
    @Override
    public void run(){
        
        PrintWriter printOut = null;
        BufferedReader readIn = null;
        int selection = 0;
        
        System.out.println("Connection successful");
        try{
            printOut = new PrintWriter(clientSocket.getOutputStream(), true);
            readIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            printOut.checkError();
            
            int num = Integer.parseInt(readIn.readLine());
            System.out.println("Waiting for input..."); 
            String userInput = readIn.readLine();
           
            try{
                selection = Integer.parseInt(userInput);
            }catch(NumberFormatException nfe){
                nfe.printStackTrace();
                System.out.println("Input needs to be a number!");
            }
        System.out.println(selection);
        boolean running = true;
           for(int i =0;i <= num;i++ ){
            switch (selection) {
                //cmd /c date is the command for Windows. This will need to be changed for linux
                //for linux it might be just date 
                // /c carries out the command execution
                case 1: {
                    System.out.println("Executing 'date'");
                    Process process = Runtime.getRuntime().exec("date");
                    
                   readIn= new BufferedReader(new InputStreamReader(
                            process.getInputStream()));
                    
                   String line;
                    while ((line = readIn.readLine()) != null) {
                        printOut.println(line);
                    }
                    System.out.println("bye");
                    printOut.println("bye");
                    process.destroy();
                    readIn.close();
                    
                   break;
                }
                
                //uptime case
                case 2: {
                    System.out.println("Executing 'uptime'");
                    Process process = Runtime.getRuntime().exec("uptime");
                    readIn = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    String line;
                    while ((line = readIn.readLine()) != null) {
                        System.out.println(line);
                        printOut.println(line);
                    }
                    process.destroy();
                    readIn.close();
                    printOut.println("bye");
                    break;
                }
                //memory use case
                case 3: {
                    System.out.println("Executing 'free'");
                    Process process = Runtime.getRuntime().exec("free");
                    readIn = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    String line;
                    
                    while ((line = readIn.readLine()) != null) {
                        System.out.println(line);
                        printOut.println(line);
                    }
                    process.destroy();
                    readIn.close();
                    printOut.println("bye");
                    break;
                }
                // netstat request case   
                case 4: {
                    System.out.println("Executing 'netstat'");
                    Process process = Runtime.getRuntime().exec("netstat");
                    readIn = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    String line;
                    while ((line = readIn.readLine()) != null) {
                        System.out.println(line);
                        printOut.println(line);
                    }
                    process.destroy();
                    readIn.close();
                    printOut.println("bye");
                    break;
                }
                //list current processes case
                case 5: {
                    System.out.println("Executing 'ps -c'");
                    Process process = Runtime.getRuntime().exec("ps -c");
                    readIn = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    String line;
                    while ((line = readIn.readLine()) != null) {
                        System.out.println(line);
                        printOut.println(line);
                    }
                    process.destroy();
                    readIn.close();
                    printOut.println("bye");
                    break;
                }
                //list current user case
                case 6: {
                    System.out.println("Executing 'who'");
                    Process process = Runtime.getRuntime().exec("who");
                    readIn = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    String line;
                    while ((line = readIn.readLine()) != null) {
                        System.out.println(line);
                        printOut.println(line);
                    }
                    process.destroy();
                    readIn.close();
                    printOut.println("bye");
                    break;
                }
                
                default: {
                    System.out.println(selection);
                    printOut.println("Please enter a number between 1 and 7");
                    selection = 1;
                    break;
                }
                
            } //end switch
            
           
          //printOut.println("Goodbye!");
         }//end for
        } catch(Exception e){
            e.printStackTrace();
        }
        
        
    }//end run()
    
}// end ThreadedServer

