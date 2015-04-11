import random
import string

class Cipher(object):
    ALPHABET = string.ascii_lowercase
    ALPHA_LEN = len(ALPHABET)

    def __init__(self, *args):
        if len(args):
            key = args[0]
            if not all([char == char.lower() and char.isalpha() for char in key]):
                raise ValueError('Key must contain only lowercase alphabetic characters!')
            self._key = key
        else:
            self._key = [random.choice(self.ALPHABET) for _ in range(100)]

        self._distances = []
        self._key_length = None

    @property
    def key(self):
        return self._key

    def encode(self, plaintext):
        return self._translate(plaintext, lambda x: x[0] + x[1])

    def decode(self, ciphertext):
        return self._translate(ciphertext, lambda x: x[0] - x[1])

    def _translate(self, message, trans_func):
        alpha = self.ALPHABET
        while len(self.key) < len(message):
            self._key *= 2

        message_alpha_indices = map(alpha.index, self._clean(message))
        key_alpha_indices = map(alpha.index, self.key)
        trans_indices = map(trans_func, zip(message_alpha_indices, key_alpha_indices))
        return ''.join([alpha[index % self.ALPHA_LEN] for index in trans_indices])

    def _clean(self, string):
        return ''.join([char.lower() for char in string if char.isalpha()])

class Caesar(Cipher):
    def __init__(self):
        super(Caesar, self).__init__('d')
