import random
import operator

abc = "abcdefghijklmnopqrstuvwxyz"

class Caesar(object):
    
    def encode(self, plain):
        return self.get_text(plain, 3)
        
    def decode(self, cipher):
        return self.get_text(cipher, -3)
        
    def get_text(self, text, offset):
        text = [c for c in text.lower() if c.isalpha()]
        output = ''
        for char in text:
            index = abc.index(char) + offset
            if index > 25:
                index -= 26
            output += abc[index]
        return output
        
        
class Cipher(object):
    
    def __init__(self, key=None):
        if key is None:
            self.key = ''.join(random.choice(abc) for i in range(150))
        elif any(c.isalpha() == False for c in key.lower()):
            raise Exception("'key' must contain only lowercase alphabet characters")
        else:
            self.key = key

    def encode(self, plain):
        return self.get_text(plain)
    
    def decode(self, cipher):
        return self.get_text(cipher, mode='dec')
        
    def get_text(self, text, mode='enc'):
        text = [c for c in text.lower() if c.isalpha()]
        
        offsets = []
        i = 0
        for c in text:
            offsets.append(abc.index(self.key[i]))
            i += 1
            if i > (len(self.key) - 1):
                i = 0
            
        op = operator.sub if mode == 'dec' else operator.add
        
        output = ''
        for i in range(0, len(text)):
            index = op(abc.index(text[i]), offsets[i])
            if index > 25:
                index -= 26
            output += abc[index]
        
        return output
