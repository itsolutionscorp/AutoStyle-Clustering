from re import sub
from math import ceil, sqrt

def clean_input( w ):
    return sub(r'[^a-z0-9]','',w.lower())

def encode( w ):
    w = clean_input( w )
    n = ceil( sqrt(len(w)) )
    
    v = ''
    for ii in range(n):
        for jj in range( ii, len(w), n ):
            v += w[ jj ]
        v += ' '
    return v.strip()
