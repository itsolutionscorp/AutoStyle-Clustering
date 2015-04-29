import itertools
import random


class Cipher:
    def __init__(self, key=None):
        if key is None:
            self.key = "".join(random.choice("abcdefghijklmnopqrstuvwxyz")
                               for _ in range(100))
        else:
            if Cipher.clean(key) != key:
                raise ValueError("The key must be all alpha and lower case.")
            self.key = key


    @staticmethod
    def add_chars(a, b):
        """
        add_chars(str, str) -> str

        Each string is of length 1. Converts both lower case characters a and b
        to ints using the order in the alphabet starting with 'a' = 0. It then
        adds both values, wrapping when needed, and converts it back to str.
        """
        return chr((ord(a) + ord(b) - ord('a') - ord('a')) % 26 + ord('a'))

    @staticmethod
    def sub_chars(a, b):
        """
        sub_chars(str, str) -> str

        Each string is of length 1. Converts both lower case characters a and b
        to ints using the order in the alphabet starting with 'a' = 0. It then
        subtracts b from a, wrapping when needed, and converts it back to str.
        """
        return chr((ord(a) - ord(b) + ord('a') - ord('a') + 26) % 26 + ord('a'))

    @staticmethod
    def substitude(plain, key, combiner):
        """
        substitude(str, str, combiner) -> str
        with combiner(str, str) -> str

        Combines plain[0] with key[0], plain[1] with key[1],... using the given
        combiner. Key is repeated when needed.
        """
        return "".join((combiner(p, k)
                        for p, k in zip(plain, itertools.cycle(key))))

    @staticmethod
    def clean(plain):
        """
        clean(str) -> str

        Return a cleaned string with just lower case alpha character.
        """
        return map(str.lower, filter(str.isalpha, plain))

    def encode(self, plain):
        return Cipher.substitude(Cipher.clean(plain), self.key, Cipher.add_chars)

    def decode(self, cipher):
        return Cipher.substitude(Cipher.clean(cipher), self.key, Cipher.sub_chars)


class Caesar(Cipher):
    def __init__(self):
        Cipher.__init__(self, key="d")
