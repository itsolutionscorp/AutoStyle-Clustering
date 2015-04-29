import string
import math

def clean_input( w ):
    wClean = ''
    for l in w:
        if l.lower() in string.ascii_lowercase or l in string.digits:
            wClean += l.lower()
    return wClean


def encode( w ):
    w = clean_input( w )
    n = math.ceil( math.sqrt(len(w)) )
    
    v = ''
    for ii in range(0,n):
        for jj in range(0,n):
            try:
                v += w[ ii + jj*n ]
            except( IndexError ):
                pass
        v += ' '
    return v.strip()
