from string import maketrans, punctuation, whitespace


s_from = 'abcdefghijklmnopqrstuvwxyz'
s_to = 'zyxwvutsrqponmlkjihgfedcba'


def encode(s):
    tmp = s.translate(maketrans(s_from+s_from.upper(), s_to+s_to)).translate(None, punctuation+whitespace)
    encode = tmp[0:5]
    for i in xrange(5,len(tmp),5):
        encode += " " + tmp[i:i+5]
    return encode
    
    
def decode(s):
    return s.translate(maketrans(s_to+s_to.upper(), s_from+s_from)).translate(None, punctuation+whitespace)
