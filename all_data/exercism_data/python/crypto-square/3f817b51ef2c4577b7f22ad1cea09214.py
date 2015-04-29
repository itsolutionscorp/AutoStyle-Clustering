from string import punctuation
from math import sqrt, ceil, floor

def encode(plaintext):
    # Normalize the text
    plaintext = plaintext.translate(None, punctuation+' ').lower()        
    ciphertext = _transpose(plaintext)

    # Add spaces every 5 ciphertext chars
    return ' '.join([ciphertext[i:i+5] for i in xrange(0,len(ciphertext),5)])

def decode(ciphertext):
    # Remove the spaces
    ciphertext = ciphertext.translate(None, ' ')
    return _transpose(ciphertext,False)

def _transpose(text, enc=True):
    
    if not text:
        return ''

    reclength = int(ceil(sqrt(len(text)))) 
    q, r = divmod(reclength**2 - len(text),reclength)
    recbreadth = reclength - q 

    if enc:
        linelengths = [reclength if i < recbreadth-1 else reclength-r for i in xrange(recbreadth)]
    else:
        linelengths = [recbreadth if i < reclength-r else recbreadth-1 for i in xrange(reclength)] 

    textsquare = []
    cur = 0

    for linelength in linelengths:
        textsquare.append(text[cur:cur+linelength] + ' '*(reclength-linelength))
        cur += linelength    

    transtext =  ''.join([''.join(word).rstrip() for word in zip(*textsquare)])
    return transtext
