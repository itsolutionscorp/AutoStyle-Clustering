import string
import textwrap
import itertools

def decode(text):
    g=gen()
    dec=str.maketrans(g[1],g[0])
    decoded=text.replace(" ","").translate(dec)
    return decoded

def encode(text):
    g=gen()
    enc=str.maketrans(g[0],g[1])
    
    #remove spaces and punctuation:
    clean=''.join(list(itertools.filterfalse(lambda x: x in string.punctuation ,text.lower().replace(" ",""))))
    
    encoded=clean.translate(enc)
    return ' '.join(textwrap.wrap(encoded,5))

def gen():
    letters=''.join(list(map(chr,range(97,123)))) #generate alphabet string
    return (letters,letters[::-1])
