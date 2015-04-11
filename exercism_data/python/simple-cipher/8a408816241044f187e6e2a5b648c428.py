import random
import string

ALPHABET = string.ascii_lowercase
DEFAULT_KEY_SIZE = 128


# noinspection PyUnresolvedReferences
class Cipher(object):
    def __init__(self, key=None):
        if key is None:
            key = ''.join(random.choice(ALPHABET) for _ in range(DEFAULT_KEY_SIZE))
        self.key = key

    def encode(self, text):
        return self._translate(text)

    def decode(self, text):
        return self._translate(text, direction=-1)

    def _translate(self, text, direction=1):
        result = []
        for index, c in enumerate(text):
            if c.lower() in ALPHABET:
                where = ALPHABET.index(c.lower())
                result.append(ALPHABET[(where + direction * self._compute_shift(index)) % len(ALPHABET)])
        return ''.join(result)

    def _compute_shift(self, index):
        return ord(self.key[index % len(self.key)]) - ord('a')


class Caesar(Cipher):
    def __init__(self):
        super(Caesar, self).__init__(key='d')
