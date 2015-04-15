import string
import re

class Caesar(object):
    def __init__(self):
        pass
        
    def encode(self, text):
        return Cipher('d' * 100).encode(text)
            
    def decode(self, text):
        return Cipher('d' * 100).decode(text)


class Cipher(object):
    def __init__(self, key=''):
        self.key = key
        self.ALPHABET = string.ascii_lowercase
    
    # helper method
    def code(self, text, shift_sign):
       # pad the end of the key with 'a' (basically no substitution)
       self.key = self.key + 'a' * len(text)
       # make text lowercase, strip out non letters, convert to list
       text = list(re.sub(r'[^a-z]','',text.lower()))
       # return result using character substitution based on key
       result = ''   
       for i in range(len(text)):
           char_value = self.ALPHABET.index(text[i])
           key_shift = shift_sign * self.ALPHABET.index(self.key[i])
           result += self.ALPHABET[(char_value + key_shift) % 26]
       return result
  
    def encode(self, text):
        return self.code(text, 1)
        
    def decode(self, text):
        return self.code(text, -1)
