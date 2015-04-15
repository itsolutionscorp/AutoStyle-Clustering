#Import statement 
try:
    
    import socket
    import sys
    from thread import *

    import webbrowser

except ImportError:
    raise SystemExit()

#Set HOST IP
HOST = '192.168.0.102'
#Set PORT NUMBER
PORT = 8988
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

try:
    #bind the host and port 
    s.bind((HOST, PORT))
    
except socket.error as msg:
    
    print "Error" + str(msg)
    sys.exit()

print("Bind Complete")
s.listen(10)
print("Listen")

def client(conn):

    conn.send('Hello')

    while True:

        data = conn.recv(1024)
        reply = 'Hello' + data
        
        if not data:
            break
        
        conn.sendall(reply)

    conn.close()


while 1:
    conn, addr = s.accept()
    print 'Connected with: ' + addr[0] + ':' + str(addr[1])

    start_new_thread(client, (conn,))
    #client_input()

s.close()
