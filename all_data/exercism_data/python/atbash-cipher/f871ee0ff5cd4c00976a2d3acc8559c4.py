def decode(msg):
    return encode(msg, True)

def encode(msg, decode=False):
     
    msg = filter(lambda a: a > '.',  msg.lower())
    #remove all punctuation characters from string
    
    out_text = ''
    
    for i, c in enumerate(msg):
        
        if i and i % 5 == 0 and not decode:
            out_text += ' '
            
        if 'a' <= c <= 'z':
            out_text += chr(25 - ord(c) + 2 * ord('a'))
        else:
            out_text += c
        
    return out_text
