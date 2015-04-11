from itertools import cycle
import random
import string


class Cipher(object):

    def __init__(self, key = None, characters = string.lowercase):
       self.characters = characters

       if key is None:
           key = self._generate_key()
       self._validate_key(key)
       self.key = key

    def _generate_key(self):
        random_characters = [random.choice(self.characters) for i in range(100)]
        return ''.join(random_characters)

    def _validate_key(self, key):
        if key != self._strip_invalid_characters(key):
            raise ValueError("Invalid key (use only characters from: " +
                             self.characters + ")");

    def encode(self, plain_text):
        plain_text = self._strip_invalid_characters(plain_text.lower())
        return self._transform(plain_text, False)

    def decode(self, encoded):
        if encoded != self._strip_invalid_characters(encoded):
            raise ValueError("Encoded data invalid")
        return self._transform(encoded, True)

    def _strip_invalid_characters(self, data):
        return "".join([c for c in data if c in self.characters])

    def _transform(self, data, for_decode = False):
        transformed = []
        direction = -1 if for_decode else +1
        key_offsets = self._key_offset_generator()
        for c, offset in zip(data, key_offsets):
            c = self._add_offset_to_character(c, direction * offset)
            transformed.append(c)
        return "".join(transformed)

    def _key_offset_generator(self):
        return cycle([self.characters.index(c) for c in self.key])

    def _add_offset_to_character(self, c, offset):
        cur_idx = self.characters.index(c)
        new_idx = (cur_idx + offset) % len(self.characters)
        return self.characters[new_idx]

class Caesar(Cipher):

    def __init__(self):
        super(Caesar, self).__init__("d")
