import itertools
import random
import string
import operator

class ArgumentError(ValueError):
    pass

def gen_string_from_alphabet(alphabet, length, random=random):
    return "".join(random.choice(alphabet) for _ in range(length))

class Cipher:
    def __init__(self, key=None, alphabet=None):
        self.alphabet = alphabet or string.lowercase
        self.to_num = {c : n for c, n in zip(self.alphabet, itertools.count())}

        if key is None:
            self.key = gen_string_from_alphabet(self.alphabet, 100,
                                                random.SystemRandom())
        elif not all(k in self.to_num for k in key):
            raise ArgumentError("The key must be from the alphabet")
        else:
            self.key = key

    def substitute(self, text, key, combiner):
        """
        substitute(str, str, combiner) -> str
        with:
            combiner(int, int) -> int

        Calculates m^(-1) (m(text) + m(key) * direction) for the
        mapping m:char->int.
        """

        text_code = [self.to_num[p] for p in text]
        key_code = [self.to_num[k] for k in key]

        cipher_code = [combiner(p, k) % len(self.alphabet)
                       for p, k in zip(text_code, itertools.cycle(key_code))]

        return "".join(self.alphabet[c] for c in cipher_code)

    def clean(self, plain):
        """
        clean(str) -> str

        Return a cleaned string with all chars not in self.to_num removed.
        """

        return "".join(p for p in plain.lower() if p in self.to_num)

    def encode(self, plain):
        return self.substitute(self.clean(plain), self.key, operator.add)

    def decode(self, cipher):
        if any(c not in self.to_num for c in cipher):
            raise ValueError("malformed cipher")

        return self.substitute(cipher, self.key, operator.sub)


class Caesar(Cipher):
    def __init__(self):
        Cipher.__init__(self, key="d")
