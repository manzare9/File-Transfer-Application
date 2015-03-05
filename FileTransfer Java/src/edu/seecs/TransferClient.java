package edu.seecs;


// First run the server side then client


//Libraries importing

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

public class TransferClient 
{
	
public static void main(String[] args) throws IOException 
{

FileInputStream fileInput = null;
BufferedInputStream bufferedInput = null;

BufferedOutputStream bufferedOuptputStream = null;

Socket socket = null;

int count;

try{
    
	try {
           socket = new Socket("127.0.0.1", 55555);
        } 
    catch (IOException ex) 
    {
            
    System.out.println("Error occurs: Unable to create Socket ");
    }

            File file = new File("sample.txt");
            
            long fileLength = file.length();
            
            System.out.println(fileLength);
            
            if ( Integer.MAX_VALUE < fileLength ) 
            {
                    System.out.println("Error occurs: It has been Exceeded the size of transfer");
            }


            byte[] bytes = new byte[(int) fileLength];
            
            try{
                        
            	fileInput = new FileInputStream(file);
                }
            catch (IOException ex)
            {
                    System.out.println("Error: Unable to open the stream of inputFile ");
                }
           
            bufferedInput = new BufferedInputStream(fileInput);
            
            bufferedOuptputStream = new BufferedOutputStream(socket.getOutputStream());

while ((count = bufferedInput.read(bytes)) > 0) 
{
    bufferedOuptputStream.write(bytes, 0, count);
}
}


finally
{
 bufferedOuptputStream.flush();
 bufferedOuptputStream.close();
 fileInput.close();
 bufferedInput.close();
 socket.close();
}
/*  byte[] bytes = new byte[(int) fileLength];
            
            try{
                        
            	fileInput = new FileInputStream(file);
                }
            catch (IOException ex)
            {
                    System.out.println("Error: Unable to open the stream of inputFile ");
                }*/


}
}