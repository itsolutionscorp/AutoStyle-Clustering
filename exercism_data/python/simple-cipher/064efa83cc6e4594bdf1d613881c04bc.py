import string
import itertools
import random

class Caesar(object):
    def __init__(self):
        self.caesar = Cipher('d')
    def encode(self, text):
        return self.caesar.encode(text)
    def decode(self, crypto):
        return self.caesar.decode(crypto)

class Cipher(object):
    def __init__(self, key=""):
        if key == "":
            randchar = lambda: chr(random.randrange(ord('a'), ord('z')+1))
            key = str().join([randchar() for i in xrange(100)])
        self.key = key
        self.discard = string.punctuation + string.digits + string.whitespace
        if key.lower().translate(None, self.discard) != key: 
            raise ArgumentError

    def encode(self, text):
        text = text.lower().translate(None, self.discard)
        keycycle = itertools.cycle(self.key)
        return str().join(itertools.imap(self._encoder, text, keycycle))
        
    def decode(self, crypto):
        keycycle = itertools.cycle(self.key)
        return str().join(itertools.imap(self._decoder, crypto, keycycle))
        
    def _encoder(self, char, shift):
        ret = ord(char) + (ord(shift) - ord('a'))
        return chr(ret) if ret <= ord('z') else chr(ret - (ord('z')-ord('a')+1))
        
    def _decoder(self, char, shift):
        ret = ord(char) - (ord(shift) - ord('a'))
        return chr(ret) if ret >= ord('a') else chr(ret + (ord('z')-ord('a')+1))
