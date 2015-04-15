import re
import string

tran_decode = string.maketrans(string.ascii_lowercase[::-1], string.ascii_lowercase)

def encode(msg):
    #Decode and encode are actually the same process
    msg = decode(msg)
    #Length of each group to insert white spaces for display
    n = 5
    return ' '.join([msg[i:i+n] for i in range(0, len(msg), n)])
    
def decode(msg):
    msg = msg.lower()
    msg = ''.join(e for e in msg if e.isalnum())
    return msg.translate(tran_decode)
