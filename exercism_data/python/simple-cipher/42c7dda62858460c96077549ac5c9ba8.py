from itertools import cycle
from operator import add, sub
import random
import string


class ArgumentError(Exception): pass

class Cipher(object):

    def __init__(self, key = None, characters = string.lowercase):
       self.characters = characters
       self.key = key or self._generate_key()
       self._validate_key()

    def _generate_key(self):
        random_characters = [random.choice(self.characters) for i in range(100)]
        return "".join(random_characters)

    def _validate_key(self):
        if any(c for c in self.key if c not in self.characters):
            raise ArgumentError("Invalid key (use only characters from: %s)" %
                                self.characters);

    def encode(self, plain_text):
        plain_text = plain_text.lower()
        plain_text = (c for c in plain_text if c in self.characters)
        return self._transform(plain_text, add)

    def decode(self, encoded):
        if any(c for c in encoded if c not in self.characters):
            raise ArgumentError(
                "The encoded data contains one or more invalid characters")
        return self._transform(encoded, sub)

    def _transform(self, data, add_or_sub):
        transformed = []
        key_offsets = self._key_offset_generator()
        for c, offset in zip(data, key_offsets):
            c = self._apply_offset_to_character(c, offset, add_or_sub)
            transformed.append(c)
        return "".join(transformed)

    def _key_offset_generator(self):
        return cycle(self.characters.index(c) for c in self.key)

    def _apply_offset_to_character(self, c, offset, add_or_sub):
        cur_idx = self.characters.index(c)
        new_idx = add_or_sub(cur_idx, offset) % len(self.characters)
        return self.characters[new_idx]

class Caesar(Cipher):

    def __init__(self):
        super(Caesar, self).__init__("d")
