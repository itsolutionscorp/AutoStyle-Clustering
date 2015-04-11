from string import ascii_lowercase
from random import choice

class Cipher(object):
    
    def __init__(self, key=None):
        if key:
            self.key = key
        else:
            self.key = ''.join([choice(ascii_lowercase) for i in range(50)])        
        
    def encode(self, text):
        #unique key_generator for encoding sequence
        conversion = self.iterkey([ord(x)-97 for x in self.key])
        #encode string comprised of characters shifted from original positions
        return self.shift(text, conversion)

        
    def decode(self, text):
        #unique key_generator for decoding sequence
        conversion = self.iterkey([-ord(x)+97 for x in self.key])
        #decode string comprised of characters shifted from original positions
        return self.shift(text, conversion)

    @staticmethod
    def shift(text, key_generator):
        #create list of letters in text
        text = [char for char in text.lower() if char.isalpha()]
        #create (shift) function that will shift character (from text) input 
            #by a number (from a key_generator)
        shift_function = lambda x, n: chr((ord(x)-97+n) % 26 + 97)
        #return a string comprised of characters shifted from original positions
        return ''.join([shift_function(char, num) 
                       for char, num in zip(text, key_generator)])
                                   
    @staticmethod
    def iterkey(key):
    #iterate over elements in key, generate letter
        while True:
            for x in key:
                yield x
        

class Caesar(Cipher):
    
    def __init__(self):
        super(Caesar, self).__init__('d')
