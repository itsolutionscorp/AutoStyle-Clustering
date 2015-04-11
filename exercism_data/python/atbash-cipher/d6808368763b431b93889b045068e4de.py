from string import maketrans
from re import sub

alph = 'abcdefghijklmnopqrstuvwxyz'
hpla = alph[::-1]

def encode(intxt):
    enc = maketrans(alph, hpla)
    return procinpt(intxt).translate(enc)

def decode(intxt):
    dec = maketrans(hpla, alph)
    return intxt.replace(" ", "").translate(dec)

def procinpt(intxt):
    
    proctxt = sub('[^a-z0-9]+', '', intxt.lower())  
    outtxt = proctxt[:5]    

    for n in range(5, len(proctxt), 5):
        outtxt += ' ' + proctxt[n:n+5]
    return outtxt
