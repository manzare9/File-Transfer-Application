#include <iostream>
#include <string.h>
using namespace std;

#include <netlink/socket.h>

int main() 
{
        NL::init();
        cout << "\nClient Side";
        cout.flush();

		NL::Socket* clientConnection = NULL;
        
		try 
		{
                NL::Socket socket("localhost", 50000);
       FILE *fp;
				int bytesRecieved=0;
				char recvBuff[256];

    memset(recvBuff, '0', sizeof(recvBuff));

    fp = fopen("output.txt", "ab"); 
    if(NULL == fp)
    {
        printf("Error opening the file");
        return 1;
    }


    while((bytesRecieved = socket.read(recvBuff,255)) > 0)
    {
        printf("Receiving the bytes %d\n",bytesRecieved);    
        
        fwrite(recvBuff, 1,bytesRecieved,fp);
       
    }

    if(bytesRecieved < 0)
    {
        printf("\n Error in receving file \n");
    }
	}
		
        catch(NL::Exception e) {
                cout << "\n" << e.what();
        }
        return 0;
}