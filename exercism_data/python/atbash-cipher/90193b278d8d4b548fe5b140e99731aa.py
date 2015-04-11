# -*- coding: utf-8 -*-
"""
Created on Thu Sep 25 20:04:33 2014

@author: johann
"""
from string import maketrans
from array import array
import re
plain = "abcdefghijklmnopqrstuvwxyz"
cipher = "zyxwvutsrqponmlkjihgfedcba"
encoder = maketrans(plain,cipher)
decoder = maketrans(cipher,plain)
def encode(message):
    
    encoded = message.lower().replace(" ","")
    encoded = re.sub("[^a-z0-9]",'',encoded)
    encoded = encoded.translate(encoder)
    encoded = list(encoded)
    for i in range (5,len(encoded)-1+len(encoded)/5,6):
        encoded.insert(i," ")
    encoded = array('B', map(ord,encoded)).tostring()
    return encoded
        
        

def decode(message):
    return message.replace(" ","").translate(decoder)
