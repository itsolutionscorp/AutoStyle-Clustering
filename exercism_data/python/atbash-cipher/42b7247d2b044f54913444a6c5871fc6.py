from string import ascii_lowercase, punctuation
#Python 3 includes str.maketrans() as a built-in function,
#and formats str.translate() differently than in Python 2.
def encode(text):
    encoded = text.replace(' ','').lower().translate(str.maketrans(ascii_lowercase, ascii_lowercase[::-1], punctuation))
    return ' '.join([encoded[i:i+5] for i in range(0, len(encoded), 5)])

def decode(text):
    return text.translate(str.maketrans(ascii_lowercase[::-1],ascii_lowercase,punctuation)).replace(' ','')
