from random import SystemRandom

class Cipher(object):
    """Performs encoding and decoding using a substitution cipher"""

    def __init__(self, *key):
        """Stores a given key or builds one if none is given"""
        if key:
            if not (key[0].islower() and key[0].isalpha()):
                raise ValueError("A key must contain only lowercase letters.")
            self._seedkey = [ord(char) - ord('a') for char in key[0]]
        else: 
            self._seedkey = [SystemRandom().randint(0, 25) for i in range(100)]
    
    def encode(self, cleartext):
        """Encodes text using the stored key"""
        #First get the text ready for encoding
        cleartext = cleartext.lower()
        cleartext = [char for char in cleartext if char.isalpha()]
        
        #Next, build a long enough encoding key from the seed key
        encode_key = self._seedkey
        while len(encode_key) < len(cleartext):
            encode_key = encode_key*2

        #Now perform the encoding and return the result
        ciphertext=[Cipher.shift(char, keyshift)
                    for char,keyshift
                    in zip(cleartext, encode_key)]
        return ''.join(ciphertext)

    def decode(self, ciphertext):
        """Decodes text using the stored key"""
        #First, build a long enough decoding key from the seed key
        decode_key = self._seedkey
        while len(decode_key) < len(ciphertext):
            decode_key = decode_key*2

        #Now perform the decoding and return the result
        cleartext=[Cipher.shift(char, -keyshift)
                   for char,keyshift
                   in zip(ciphertext, decode_key)]
        return ''.join(cleartext)

    @staticmethod
    def shift(character, shift):
        """Shifts a character by a given number,
        Makes sure the result is in the range 'a'-'z'.        
        """
        charval = ord(character)
        charval += shift
        if charval < ord('a'):
            charval += 26
        elif charval > ord('z'):
            charval -= 26
        return chr(charval)
                
class Caesar(Cipher):
    """Performs encoding and decoding using the Caesar cipher"""

    def __init__(self):
        """Sets up the Cipher to perform the Caesar shift"""
        self._seedkey = [3]
