from random import choice


class Cipher:
    def __init__(self, key=None):
        self.word_range = list(range(ord('a'), ord('z') + 1))
        self.num = dict((chr(v), i) for i, v in enumerate(self.word_range))

        if key is None:
            self.key = ''.join(choice(list(map(chr, self.word_range))) for _ in range(100))
        else:
            self.key = key

    def coder(self, words='', crypt=True):
        if not self.key.islower() or not self.key.isalpha():
            raise ValueError('Key must be only lower ascii charters')

        words = list(map(ord, words.replace(' ', '').lower()))
        new_string = ''
        klen = len(self.key) - 1
        point = 0
        for i in words:

            if point > klen:
                point = 0

            p = self.num[self.key[point]]

            if crypt:
                j = i + p
                if j > self.word_range[-1]:
                    j = (j % self.word_range[-1]) + self.word_range[0] - 1

            else:
                j = i - p
                if j < self.word_range[0]:
                    j = self.word_range[-1] - (self.word_range[0] - j) + 1

            if j >= self.word_range[0] and j <= self.word_range[-1]:
                new_string += chr(j)

            point += 1

        return new_string

    def decode(self, words=''):
        return self.coder(words, False)

    def encode(self, words=''):
        return self.coder(words)


class Caesar(Cipher):
    def __init__(self):
        super().__init__(key='d')
