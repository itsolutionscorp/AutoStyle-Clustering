import random
import string

alpha = string.ascii_lowercase

class Caesar:
    @classmethod
    def encode(cls, cleartext):
        shifted = alpha[3:] + alpha[:3]
        return cleartext.lower().translate(str.maketrans(alpha, shifted, ' ,.!?1234567890\\\''))

    @classmethod
    def decode(cls, ciphertext):
        shifted = alpha[3:] + alpha[:3]
        return ciphertext.translate(str.maketrans(shifted, alpha))

class Cipher:
    def __init__(self, key=""):
        if key=="":
            self.key = Cipher.randomKey()
        else:
            self.key = key

    @classmethod
    def randomKey(cls):
        return ''.join(random.choice(string.ascii_lowercase) for i in range(0, 150))

    def encode(self, cleartext):
        return ''.join([chr((ord(cleartext[i]) - ord('a') + ord(self.key[i % len(self.key)]) - ord('a')) % 26 + ord('a')) for i in range(0, len(cleartext))])

    def decode(self, ciphertext):
        return ''.join([chr((ord(ciphertext[i]) - ord(self.key[i % len(self.key)]) + 26) % 26 + ord('a')) for i in range(0, len(ciphertext))])
