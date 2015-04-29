from math import ceil, sqrt
    
def encode(msg):
    msg = ''.join([c for c in msg if c.isalnum()]).lower()
    size = int(ceil(sqrt(len(msg))))
    #Pad message
    msg = msg + ' '*(size**2 - len(msg))
    msg2=[]
    i = 0
    for c in range(size):
        for r in range(size):
            i = (r*size + c)
            if msg[i] != ' ':
                msg2.append(msg[i])
        msg2.append(' ')
    msg2 = ''.join(c for c in msg2)
    return msg2.strip()
