package edu.seecs;


import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TransferServer 
{


public static void main(String[] args) throws IOException 
{
	
    ServerSocket serverSocket = null;
    Socket socket = null;
    InputStream inputStream = null;
    FileOutputStream fileOutputStream = null;
    BufferedOutputStream bufferedOutputStream = null;
    
    int sizeBuffer = 0;

    try{

            try 
            {
                  serverSocket = new ServerSocket(55555);
            } 
            catch (IOException ex)
            {
                    System.out.println("Error occurs: Unable to Connect to the Server ");
            }
            System.out.println("The Socket created ");
            
            
            try {
                    socket = serverSocket.accept();
                } catch (IOException ex) {
                    System.out.println("Error occurs: Unable to Connect to the client ");
                }
            
            System.out.println("Accepted Client Connection ");
            
            try {
                    inputStream = socket.getInputStream();
                    sizeBuffer = socket.getReceiveBufferSize();
                    System.out.println("Size of Buffer " + sizeBuffer);
                } 
            catch (IOException ex) 
            {
                        System.out.println("Error: unable to ger socket input stream ");
                }       

            try {
                    fileOutputStream = new FileOutputStream("output.txt");
                    bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

                } 
            catch (FileNotFoundException ex) 
            {
                        System.out.println("File not found. ");
                }

            byte[] bytes = new byte[sizeBuffer];

            int count;

            while ((count = inputStream.read(bytes)) > 0) {
                    bufferedOutputStream.write(bytes, 0, count);
                   
            }

            System.out.println("\nFile has been uploaded on Server...! \nAnd created in the Directory!");
    }
    
    
    /*try {
        inputStream = socket.getInputStream();
        sizeBuffer = socket.getReceiveBufferSize();
        System.out.println("Size of Buffer " + sizeBuffer);
    } 
catch (IOException ex) 
    */
    
    
finally
{
bufferedOutputStream.flush();
bufferedOutputStream.close();
inputStream.close();
socket.close();
serverSocket.close();
}
}

}