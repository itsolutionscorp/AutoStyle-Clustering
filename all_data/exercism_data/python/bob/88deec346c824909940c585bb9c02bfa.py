# -*- coding: utf-8 -*-

def hey(msg):
    if msg.isupper(): return "Whoa, chill out!"
    elif msg.strip().endswith("?"): return "Sure."
    elif msg.strip() == "": return "Fine. Be that way!"
    return "Whatever."
    


