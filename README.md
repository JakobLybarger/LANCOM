# LANCOM

LANCOM stands for Local Area Network Communication which is exactly what this application is. With LANCOM you will be able to chat with anyone else on your LAN(Local Area Network) who is also running the client side of the application.

## Installation and Setup

To install LANCOM on your computer follow the following steps: 
1. cd to the directory in your file system where you want LANCOM to be cloned
2. Clone the repository in the desired directory
    ```sh
    $ git clone https://github.com/JakobLybarger/LANCOM.git
    ```
For the program to work two the server side code and the client side code have to be ran.
The server side code only needs to be ran by one person. To run it:
1. cd into or open the terminal up in the src file for the server code
2. ```sh
    $ javac Main.java Server.java User.java
    ```
3. ```sh
    $ java Main <Port number> <Backlog number> <IP address>
    ```
4. The server is now up and running.

Now for the client side code which has to be ran by everyone that wants to join the server to chat:
1. cd into or open the terminal up in the src file for the client code
2. ```sh
    $ javac Main.java Client.java Read.java Write.java
    ```
3. ```sh
    $ java Main <IP address> <Port number>
    ```
4. Client is now connected to the server.

## How To Use

Once you are connected to the server all you have to do is type in whatever you wish to send to the others on the server and hit enter. If you wish to leave simply type in "bye" and hit enter. Both the server and the other users will be notified that you left.
