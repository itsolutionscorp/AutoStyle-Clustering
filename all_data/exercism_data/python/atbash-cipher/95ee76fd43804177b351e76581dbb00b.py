from string import maketrans

FROM = "abcdefghijklmnopqrstuvwxyz"
TO = "zyxwvutsrqponmlkjihgfedcba"
def encode(msg):
    msg = msg.lower()
    msgList = list(msg.translate(maketrans(FROM, TO), " ,."))    
    OFFSET = 6    
    for i in range(0, len(msgList) + len(msgList)/OFFSET, OFFSET):               
        if i % OFFSET == 0:
            msgList.insert(i, " ")         
    return ''.join(msgList).strip()
    
def decode(msg):
    return msg.translate(maketrans(FROM, TO), " ,.")
