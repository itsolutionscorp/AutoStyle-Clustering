from string import ascii_lowercase as az
from string import punctuation,digits

def decode(text):
    decyphered = encode(text)
    return decyphered.replace(" ",'')

def encode(text):
    text = text.strip().replace(" ",'').lower()
    text = "".join(c for c in text if c not in punctuation)
    cyph = dict(zip(az,az[::-1]))
    cyph.update(zip(digits,digits))
    cyph  = ''.join(cyph[c] for c in text)
    cyphered = ''
    for n,c in enumerate(cyph):
        cyphered += c
        if (n+1) % 5 == 0:  # 5 letters words
            cyphered += " "
    return cyphered.strip()

