import re
from string import maketrans
import random
class Caesar(object):

    def __init__(self):
        source = "abcdefghijklmnopqrstuvwxyz"
        translate = "defghijklmnopqrstuvwxyzabc"
        self.encodetable = maketrans(source,translate)
        self.decodetable = maketrans(translate,source)

    def encode(self,msg):
        return re.sub("[^a-z]+","",msg.lower()).translate(self.encodetable)

    def decode(self,msg):
        return msg.translate(self.decodetable)        
        

class Cipher(object):

    letters = 'abcdefghijklmnopqrstuvwxyz'

    def __init__(self,key=None):
        if key:
            self.key = key
        else:
            self.key = ""
            for i in range(128):
                self.key += random.choice(Cipher.letters)


    def __encode_char(self,source,key):
        source_int =ord(source)-97
        key_int = ord(key)-97
        return chr((source_int+key_int)%26 + 97)

    def __decode_char(self,source,key):
        source_int = ord(source)-97
        key_int = ord(key)-97
        return chr((source_int-key_int)%26 + 97)

    def __code(self,txt,code_function):
        key = self.key
        while len(key) < len(txt):
            key += key
        encoded = ""
        for i in range(len(txt)):
            encoded += code_function(txt[i],key[i])
        return encoded

    def decode(self,txt):
        return self.__code(txt,self.__decode_char)

    def encode(self,txt):
        return self.__code(txt,self.__encode_char)
