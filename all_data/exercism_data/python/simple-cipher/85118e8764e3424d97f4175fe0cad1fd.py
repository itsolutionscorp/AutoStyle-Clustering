# cgi path
# William Morris
# exercism.io
# cipher.py

import random

class Caesar:

    def __init__(self):
        self.key = 'd'
        
    def encode(self,phrase):
        encoded_phrase = ''
        for letter in phrase:
            if letter.isalpha():
                encoded_phrase += _shift(letter.lower(),self.key,1)
        return encoded_phrase
                
            
    def decode(self,phrase):
        decoded_phrase = ''
        for letter in phrase:
            if letter.isalpha():
                decoded_phrase += _shift(letter.lower(),self.key,-1)
        return decoded_phrase



class Cipher:

    def __init__(self, key = None):
        if key:
            self.key = key
        else:
            self.key = ''.join([chr(random.randint(97,122)) for i in range(100)])
        
    def encode(self,phrase):
        keylist = list(self.key)
        phrase = list(phrase)
        while len(keylist) < len(phrase):
            keylist += keylist
        encoded_phrase = ''
        for letter,key in zip(phrase,keylist):
            if letter.isalpha():
                encoded_phrase +=_shift(letter,key,1)
        return encoded_phrase    
        
        
    def decode(self,phrase):
        keylist = list(self.key)
        while len(keylist) < len(phrase):
            keylist += keylist
        decoded_phrase = ''    
        for letter,key in zip(phrase,keylist):
            if letter.isalpha():
                decoded_phrase +=_shift(letter,key,-1)
        return decoded_phrase    

def _shift(letter,key,sign):
    '''letter and key must be lower case, sign must be 1 (for encode)
    or -1 (for decode)'''
    shift = ord(key)-97
    letter_ascii = ord(letter)
    letter_ascii += sign*shift
    while letter_ascii < 97 or letter_ascii > 122:
        letter_ascii -= sign*26
    return chr(letter_ascii)
    
