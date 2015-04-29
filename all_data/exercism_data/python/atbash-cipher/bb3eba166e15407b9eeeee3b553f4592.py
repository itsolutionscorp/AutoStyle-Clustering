import string
def decode(encoded):
    return encoded.lower().translate(encoded.maketrans('','',string.whitespace)).translate(encoded.maketrans(string.ascii_lowercase,string.ascii_lowercase[::-1]))

def encode(self):
    encoded = decode(self).translate(self.maketrans('','',string.punctuation))
    return ' '.join(encoded[a:a+5] for a in range(0,len(encoded),5))
