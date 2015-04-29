import itertools
import random
import string


class Cipher:
    def __init__(self, key=None, alphabet=None):
        self.alphabet = alphabet or string.lowercase
        self.to_num = {c : n for c, n in zip(self.alphabet,
                                             itertools.count())}


        if key is None:
            self.key = "".join(random.choice(self.alphabet)
                               for _ in range(100))
        else:
            if any((k not in self.alphabet for k in key)):
                raise ValueError("The key must be from the alphabet")

            self.key = key

    def substitute(self, plain, key, direction=1):
        """
        substitute(str, str, direction) -> str

        Calculates m^(-1) (m(plain) + m(key) * direction) for the
        mapping m:char->int.
        """

        cipher = []
        for p, k in zip(plain, itertools.cycle(key)):
            p, k = self.to_num[p], self.to_num[k]
            c = (p + k * direction) % len(self.alphabet)
            cipher.append(self.alphabet[c])

        return "".join(cipher)

    def clean(self, plain):
        """
        clean(str) -> str

        Return a cleaned string with all chars not in self.to_num removed.
        """

        return "".join((p for p in plain.lower() if p in self.to_num))

    def encode(self, plain):
        return self.substitute(self.clean(plain), self.key, 1)

    def decode(self, cipher):
        if any((c not in self.to_num for c in cipher)):
            raise ValueError("malformed cipher")

        return self.substitute(cipher, self.key, -1)


class Caesar(Cipher):
    def __init__(self):
        Cipher.__init__(self, key="d")
