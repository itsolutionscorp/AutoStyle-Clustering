from string import maketrans
from re import sub

_ALPH = 'abcdefghijklmnopqrstuvwxyz'
_HPLA = _ALPH[::-1]
_CIPH = maketrans(_ALPH, _HPLA)

def encode(intxt):
    return procinpt(intxt).translate(_CIPH)

def decode(intxt):
    return intxt.lower().replace(' ', '').translate(_CIPH)

def procinpt(intxt): 
    proctxt = sub('[^a-z0-9]+', '', intxt.lower())  
    return ' '.join([proctxt[n:n+5] for n in range(0, len(proctxt), 5)])

#thank you @marz619 for the input
