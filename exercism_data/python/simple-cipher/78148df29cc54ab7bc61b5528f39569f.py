import string
import random

ALPHABET = string.ascii_lowercase


class Caesar:
    def __init__(self):

        self.cipher = "".join([ALPHABET[(i + 3) % 26] for i in range(26)])

    def encode(self, message):
        return message.lower().translate(str.maketrans(ALPHABET, self.cipher, string.whitespace + string.punctuation + string.digits))

    def decode(self, message):
        return message.lower().translate(str.maketrans(self.cipher, ALPHABET))


class Cipher:
    __rand_key = ''.join(random.choice(ALPHABET) for _ in range(100))

    def __init__(self, key=__rand_key):
        self.key = key

    def __shift(self, message):
        output = ""
        for index, letter in enumerate(message):
            offset = 0
            if index < len(self.key):
                offset = ALPHABET.index(self.key[index])
            output += ALPHABET[(ALPHABET.index(letter) + offset * self.direction) % 26]
        return output

    def encode(self, message):
        self.direction = 1
        return self.__shift(message)

    def decode(self, message):
        self.direction = -1
        return self.__shift(message)
