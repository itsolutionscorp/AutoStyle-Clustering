'''exer cipher'''

import random
import string

class Cipher:
    '''implement a simple substitution cipher encode/decode mechanism'''

    def __init__(self, key=''):
        '''init the key, make up a 100char random key if not specified'''

        self.char_map = string.ascii_lowercase
        if key == '':
            key = ''.join(random.choice(self.char_map) for _ in range(100))
        self.key = key.lower()

    def lengthen_key(self, buf):
        '''lengthen key to be as long as buffer'''
        key = self.key
        while len(key) < len(buf):
            key += self.key
        return key

    def encode_char(self, plain, key):
        '''encode a single plain char with a single key char'''
        try:
            ndx_plain = self.char_map.index(plain.lower())
            ndx_key = self.char_map.index(key)
            len_chars = len(self.char_map)
            encoded = self.char_map[(ndx_plain + ndx_key) % len_chars]
        except ValueError:
            encoded = ''

        return encoded

    def encode(self, plain_text):
        '''encode the plain_text using self.key'''
        cipher_text = ''
        key = self.lengthen_key(plain_text)
        for pchar, kchar in zip(plain_text, key):
            cipher_text += self.encode_char(pchar, kchar)

        return cipher_text

    def decode_char(self, cipher, key):
        '''decode a single cipher char with a single key char'''
        len_chars = len(self.char_map)
        ndx_cipher = self.char_map.index(cipher)
        ndx_key = self.char_map.index(key)
        return self.char_map[(ndx_cipher + len_chars - ndx_key) % len_chars]

    def decode(self, cipher_text):
        '''decode the cipher text using self.key'''
        plain_text = ''
        key = self.lengthen_key(cipher_text)
        for cchar, kchar in zip(cipher_text, key):
            plain_text += self.decode_char(cchar, kchar)

        return plain_text

class Caesar(Cipher):
    '''implement a simple caesar cipher rot 3'''
    def __init__(self):
        Cipher.__init__(self, key='d' * 100)
