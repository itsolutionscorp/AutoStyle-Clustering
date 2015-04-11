from string import ascii_lowercase

class Cipher():
    def __init__(self,key='d'):
        shift = ord(key[0])-ord('a')
        self._encode = {c:ascii_lowercase[(idx+shift)%26]
                        for idx,c in enumerate(ascii_lowercase)}
        self._decode = {ascii_lowercase[(idx+shift)%26]:c
                        for idx,c in enumerate(ascii_lowercase)}

    def encode(self, text):
        text = text.lower()
        return "".join( self._encode.get(c,"") for c in text)

    def decode(self, cryptext):
        cryptext = cryptext.lower()
        return "".join( self._decode.get(c,"") for c in cryptext)

class Caesar(Cipher):
    pass


