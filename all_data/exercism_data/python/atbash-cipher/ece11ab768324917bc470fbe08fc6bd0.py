import re
from string import maketrans, ascii_lowercase


def encode(seq):
    encode=maketrans(ascii_lowercase , ascii_lowercase[::-1])
    
    encoded= normalize(seq).translate(encode)
    
    return ' '.join( [encoded[i:i+5] for i in range(0, len(encoded), 5)])

def decode(seq):
    decode=maketrans(ascii_lowercase[::-1],ascii_lowercase)

    return normalize(seq.translate(decode))

def normalize(seq):
    seq=re.sub('[.,!;]','',seq).lower()
    seq=re.sub(' ','',seq)

    return seq
