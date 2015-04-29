from string import Formatter as f
from string import index, rindex, ascii_lowercase
from re import sub

class Caesar(object):
        
    @classmethod
    def encode(cls, text, shift=None):
        if shift == None:
            shift = [3]*len(text)
        if len(shift) <= len(text):
            shift *= len(text)
        else:
            pass
        text = (sub('[^a-zA-Z]','', text)).lower()
        new = ''
        for n in xrange(0, len(text)):
            x = f().get_value(n, text, None)
            z = f().get_value((index(ascii_lowercase, x))+shift[n], ascii_lowercase*2, None)
            new += z
        return new
        
    @classmethod
    def decode(cls, text, shift=None):
        if shift == None:
            shift = [3]*len(text)
        elif shift < len(text):
            shift *= len(text)
        else:
            pass
        text = (sub('[^a-zA-Z]','', text)).lower()
        new = ''
        for n in xrange(0, len(text)):
            x = f().get_value(n, text, None)
            z = f().get_value((rindex(ascii_lowercase, x))-shift[n], ascii_lowercase*2, None)
            new += z
        return new

class Cipher(Caesar):
    
    def __init__(self, 
            key='duxrceqyaimciuucnelkeoxjhdyduucpmrxmaivacmybmsdrzwqxvbxsygzsabdjmdjabeorttiwinfrpmpogvabiofqexnohrqu'):
        self.key = key
        
        self.shift = [index(ascii_lowercase, i) for i in list(key)]
        
    def encode(self, text):
        return super(Cipher, self).encode(text, self.shift)
    def decode(self, text):
        return super(Cipher, self).decode(text, self.shift)
