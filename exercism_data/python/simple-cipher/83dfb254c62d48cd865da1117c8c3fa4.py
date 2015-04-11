#cipher.py
#xoty*rokiom^
import random


class Cipher():

    def __init__(self, key=None):

        if key is None:
            self.key = self.generate_key()
        else:
            if not key.isalpha():
                Exception('key contains non alpha characters')
            if not key.islower():
                Exception('key contains upper-case characters')
            self.key = key

    def encode(self, message):
        encrypted = ''
        for c in range(0, len(message)):
            encrypted += chr(((ord(message[c])-97 + ord(self.key[c % len(self.key)])-97) % 26) + 97)
        return encrypted

    def decode(self, message):
        decrypted = ''
        for c in range(0, len(message)):
            decrypted += chr(((((ord(message[c]))-97) - (ord(self.key[c % len(self.key)])-97)) % 26) + 97)
        return decrypted

    @staticmethod
    def generate_key():
        char_dict = list('abcdefghijklmnopqrstuvwxyz')
        key = ''
        for i in range(0, 100):
            key += (char_dict[random.randint(0, len(char_dict)-1)])
        return key


class Caesar():

    def __init__(self):
        self.c = Cipher('dddddddddddddddd')

    def encode(self, message):
        message = ''.join(ch for ch in message if ch.isalpha())
        return self.c.encode(message.lower())

    def decode(self, message):
        return self.c.decode(message)
