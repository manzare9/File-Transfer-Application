#include <iostream>
using namespace std;

#include <netlink/socket.h>


int main() 
{
        NL::init();
     
		cout << "\nStarting the Server...!";
        cout.flush();

        NL::Socket* clientConnection = NULL;

        try {
                NL::Socket server(5000);                
                clientConnection = server.accept();
                
	         

		FILE *fp = fopen("sample.txt","rb");
        
		if(fp==NULL)
        {
            printf("Error Oppening the File");
            return 1;   
        }   
               
        while(1)
        {
           
            unsigned char buff[256]={0};

            int nread = fread(buff,1,256,fp);

            printf("Reading Bytes %d \n", nread);        

          
            if(nread > 0)
            {
                printf("Sending the file and data \n");
              
				clientConnection->send(buff, nread);
            }

   
            if (nread < 256)
            {
                if (feof(fp))
                    printf("End of file\n");
                if (ferror(fp))
                    printf("Error reading the file\n");
                break;
            }
               

                delete clientConnection;
                cout << "\nClient Connection Ended!";
        }
		}
        catch(NL::Exception e) 
		{
                cout << "\n" << e.what();
                
                if(clientConnection != NULL)
                        delete clientConnection;
        }
        return 0;
}
