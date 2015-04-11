import string, math, re
class Caesar():
    
    alphadict = {letter:i for i,letter in enumerate(string.ascii_lowercase)}
    alphalist = [letter for letter in string.ascii_lowercase]
    
    def __init__(self, key='d'):
        self.key = key
        
    def extend_key(self, ptextlen, keylen):
        exkey = self.key
        exkey *= int(math.ceil(float(ptextlen) / keylen))
        return exkey
    
    def striptext(self, text):
        reg = re.compile('[%s]' % re.escape(string.punctuation+string.whitespace+string.digits))
        return  reg.sub('', text)
        
    def encode(self, ptext, decode=False):
        exkey = self.extend_key(len(ptext), len(self.key))
        ctext = ''
        
        for i, letter in enumerate(self.striptext(ptext).lower()):
            if decode:
                offset = self.alphadict[letter] - self.alphadict[exkey[i]]
            else:
                offset = self.alphadict[letter] + self.alphadict[exkey[i]]
            ctext += self.alphalist[offset % 26]
        return ctext
        
    def decode(self, ctext):
        return self.encode(ctext, True)
            
class Cipher(Caesar):
    pass
