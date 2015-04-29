from math import ceil, sqrt
    
def encode(msg):
    msg = ''.join([c for c in msg if c.isalnum()]).lower()
    size = int(ceil(sqrt(len(msg))))
    # Padding not required
    msg2 = ''
    for i in range(size):
        for j in range(i, len(msg), size):
            msg2 += msg[j]
        msg2 += ' '
    return msg2[:-1]
    
def decode(msg):
    msg2 = ''
    w = [list(w) for w in msg.split()]
    size = len(w[0])
    for i in range(size):
        for c in w:
            if c:
                msg2 += c.pop(0)
    return msg2    
    
    
if __name__ == '__main__':
     print(decode(encode('This is easy!')) )  
