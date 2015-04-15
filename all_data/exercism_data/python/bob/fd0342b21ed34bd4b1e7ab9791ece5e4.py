'''
Created on Sep 26, 2014

@author: exp
'''
def hey(talk):
    reply = 0
    if len(talk) < 1:
        return "Fine. Be that way!"
    for x in range(len(talk)):
        if ord(talk[x]) >= 65 and ord(talk[x]) <= 90 or ord(talk[x]) >= 192 and ord(talk[x]) <= 221:
            if reply is not 3:
                reply = 1
        elif ord(talk[x]) >= 97 and ord(talk[x]) <= 122 or ord(talk[x]) >= 224 and ord(talk[x]) <= 255:
            reply = 3
        elif ord(talk[x]) > 32:
            if reply == 1:
                reply = 1
            else:
                reply = 4
    if talk[len(talk)-1] == "?" and reply is not 1:
        reply = 2
    if reply == 0:
        return "Fine. Be that way!"
    elif reply == 1:
        return "Whoa, chill out!"
    elif reply == 2:
        return "Sure."
    else:
        return "Whatever."
        
    pass
        
        
