import string
import re
import random

class Cipher(object):
    def __init__(self, key=None):
        self.ALPHABET = string.ascii_lowercase
        if key == None:
            self.key = ''
            for i in range(100):
                self.key += self.ALPHABET[random.randrange(26)]
        else:
            self.key = key

    
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



class Caesar(Cipher):
    def __init__(self):
        super(Caesar, self).__init__('d' * 100)
