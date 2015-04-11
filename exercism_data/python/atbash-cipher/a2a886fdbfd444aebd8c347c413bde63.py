#!/usr/bin/env python
import string

intab = 'abcdefghijklmnopqrstuvwxyz'
outtab ='zyxwvutsrqponmlkjihgfedcba'

def decode(secret):
    table = string.maketrans(intab, outtab)
    return secret.translate(table, string.whitespace)

def encode(secret):
    secret = secret.lower()
    table = string.maketrans(intab,outtab)
    secret = secret.translate(table, string.punctuation)
    secret = secret.translate(None, string.whitespace)
    for i in range(len(secret)/5):
        insertion = (5*(i+1)) + i
        secret = secret[:insertion]+" "+secret[insertion:]

    return secret.strip()
    
