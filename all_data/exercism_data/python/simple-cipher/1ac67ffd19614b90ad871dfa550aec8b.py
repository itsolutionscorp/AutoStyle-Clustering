from string import ascii_lowercase
import random

# Global constants
ord_a = ord('a')
ord_z = ord('z')
distance_a_to_z = len(ascii_lowercase)

class Cipher:

    def __init__(self, key=None):
        if key:
            if key.isalpha() and key.islower():
                self.key = key
            else:
                    #ArgumentError
                raise ValueError(key)
        else:
            random.seed()
            self.key = ''.join(random.choice(ascii_lowercase)
                               for i in range(100))

    def encode(self, s):
        sl = ''.join(c for c in s.lower() if c in ascii_lowercase)
        extended_key = self.key * (len(sl) // len(self.key) + 1 )
        return(''.join(self.shift_right(c, k) for c, k in zip(sl, extended_key)))  

    def decode(self, s):
        # encoded message will always be lower case and be only ascii letters
        extended_key = self.key * (len(s) // len(self.key) + 1)
        return(''.join(self.shift_left(c, k) for c, k in zip(s, extended_key)))  

    def shift_right(self,chr_in,key):
        new_chr=(ord(chr_in)+(ord(key)-ord_a))
    
        # check and fix if new chr is not in ascii lowercase
        if new_chr > ord_z: new_chr -= distance_a_to_z
        return chr(new_chr)

    def shift_left(self,chr_in,key):
        new_chr = (ord(chr_in)-(ord(key)-ord_a))

        # check and fix if new chr is not in ascii lowercase
        if new_chr < ord_a: new_chr += distance_a_to_z
        return chr(new_chr)
    

class Caesar(Cipher):

    def __init__(self):
        Cipher.__init__(self, 'd')

    
