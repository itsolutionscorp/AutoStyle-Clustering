import re
from string import maketrans

plain = 'abcdefghijklmnopqrstuvwxyz'
cipher = 'zyxwvutsrqponmlkjihgfedcba'
tran_encode = maketrans(plain, cipher)
tran_decode = maketrans(cipher, plain)

def encode(msg):
    msg = msg.lower()
    #Only keep alphanumeric characters, remove the rest
    msg = ''.join(e for e in msg if e.isalnum())
    msg = msg.translate(tran_encode)
    n = 5
    return ' '.join([msg[i:i+n] for i in range(0, len(msg), n)])
    
def decode(msg):
    msg = msg.lower()
    msg = ''.join(e for e in msg if e.isalnum())
    return msg.translate(tran_decode)
