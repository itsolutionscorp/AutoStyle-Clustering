from string import ascii_lowercase, whitespace, punctuation, digits
from operator import add, sub
from os import urandom

class ArgumentError(Exception):
    pass

class Cipher(object):

    default_key_len = 100

    def __init__(self, key=None, excluded=None, alphabet=None):
        self.alphabet = alphabet or ascii_lowercase
        self.alphabet_len = len(self.alphabet)
        self.excluded = excluded or whitespace + punctuation + digits
        self.key = key or self._generate_key()
        if any(k not in self.alphabet for k in self.key):
            raise ArgumentError("Key contains characters not in alphabet ({0})".format(self.alphabet))

    def _generate_key(self):
        randomness = urandom(self.default_key_len)
        key_indexes = [ord(r) % self.alphabet_len for r in randomness]
        return "".join(self.alphabet[k] for k in key_indexes)

    def _wrap_alphabet_idx(self, shift_idx):
        if shift_idx >= self.alphabet_len:
            return shift_idx - self.alphabet_len
        if shift_idx < 0:
            return self.alphabet_len + shift_idx
        return shift_idx

    def _shift_char(self, msg_idx, char, shift_operator=add):
        char_key = self.key[msg_idx % len(self.key)]
        shift_idx = shift_operator(self.alphabet.index(char), self.alphabet.index(char_key))
        shift_idx = self._wrap_alphabet_idx(shift_idx)
        return self.alphabet[shift_idx]

    def _sanitize_message(self, message):
        return message.lower().translate(None, self.excluded)

    def encode(self, message):
        message = self._sanitize_message(message)
        return "".join(self._shift_char(i, c) for i, c in enumerate(message))

    def decode(self, message):
        return "".join(self._shift_char(i, c, sub) for i, c in enumerate(message))

class Caesar(Cipher):
    def __init__(self):
        super(Caesar, self).__init__(key="d")
