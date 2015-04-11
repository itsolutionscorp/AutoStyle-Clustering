from math import ceil
import random
from string import ascii_lowercase

class Caesar:

    alphabet = [a for a in ascii_lowercase]

    def filter(self, phrase):
        return [c.lower() for c in phrase if c.isalpha()]

    def translate(self, letter, distance):
        return self.alphabet[(self.alphabet.index(letter) + distance) % len(self.alphabet)]

    def encode(self, phrase, distance=3):
        phrase = self.filter(phrase)
        return ''.join(self.translate(letter, distance) for letter in phrase)

    def decode(self, phrase):
        return self.encode(phrase, distance=-3)


class Cipher(Caesar):

    def __init__(self, *args):
        self.mask = [self.alphabet.index(letter) for letter in list(args[0] if args else self.random_mask())]

    def random_mask(self):
        return random.sample(self.alphabet * 4, 100)

    def encode(self, phrase, decode=False):
        phrase = self.filter(phrase)

        mask = self.mask * int(ceil(float(len(phrase)) / float(len(self.mask))) or 1)
        negative_mask = map(lambda x: x * -1, mask)

        return ''.join(self.translate(letter, distance) for letter, distance in zip(phrase, mask if not decode else negative_mask))
        
    def decode(self, phrase):
        return self.encode(phrase, decode=True)
