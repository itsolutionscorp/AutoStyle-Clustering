# -*- coding: utf-8 -*-
"""
Created on Tue Oct 21 10:20:30 2014

@author: Blair
"""

import string

cipherTable = string.maketrans("abcdefghijklmnopqrstuvwxyz", "zyxwvutsrqponmlkjihgfedcba")

def decode(cipherString):  
    ## delete spaces when translating
    return cipherString.translate(cipherTable, " ")
    
def encode(plainString):
    ## remove spaces and punctuation when translating
    plainString = plainString.lower()
    plainString = plainString.translate(cipherTable, " "+ string.punctuation)
    ## insert a space every 5 characters
    i = 5
    while i < len(plainString):
        plainString = plainString[:i] + " " + plainString[i:]
        i += 6     
        
    return plainString
    
