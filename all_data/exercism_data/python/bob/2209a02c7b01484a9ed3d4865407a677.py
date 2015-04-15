# -*- coding: utf-8 -*-

def hey(msg):
    msg=msg.strip()
    if msg.isupper():
        return "Whoa, chill out!"
    elif msg.endswith("?"):
        return "Sure."
    elif msg == "":
        return "Fine. Be that way!"
    return "Whatever."
    


      
