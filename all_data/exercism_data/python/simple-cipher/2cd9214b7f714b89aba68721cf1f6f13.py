from string import ascii_lowercase
from random import choice

class Cipher:
    def __init__(self, key=None):
        if key:
            self.key = _remove_non_alpha(key)
        else:
            self.key = ''.join([choice(ascii_lowercase) for i in range(100)])

    def _translate(self, msg, shift_char):
        normalized_key = self.key * (len(msg) // len(self.key) + 1)
        return ''.join(shift_char(char, key) for char, key in zip(msg, normalized_key))

    def encode(self, msg):
        shift_char = lambda char, key: chr(((ord(char) + ord(key) - 2 * ord('a'))
                                           % len(ascii_lowercase)) + ord('a'))
        return self._translate(_remove_non_alpha(msg), shift_char)

    def decode(self, msg):
        shift_char = lambda char, key: chr(((ord(char) - ord(key) + len(ascii_lowercase))
                                        % len(ascii_lowercase)) + ord('a'))
        return self._translate(msg, shift_char)

class Caesar(Cipher):
    def __init__(self):
        Cipher.__init__(self, 'd')

def _remove_non_alpha(str):
    return ''.join([c for c in str if c.isalpha()]).lower()
