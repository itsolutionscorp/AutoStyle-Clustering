import random


class Robot(object):

    in_use = set()

    def __init__(self):
        self._name = None

    @property
    def name(self):
        if self._name is None:
            self.generate_name()
        return self._name

    def reset(self):
        if self._name is not None:
            self.in_use.remove(self._name)
            self._name = None

    letters = "".join([chr(ord('A')+i) for i in range(26)])
    numbers = "0123456789"

    @classmethod
    def letter(cls):
        return random.choice(cls.letters)

    @classmethod
    def number(cls):
        return random.choice(cls.numbers)

    @classmethod
    def random_name(cls):
        return "".join([cls.letter(), cls.letter(),
                        cls.number(), cls.number(), cls.number()])

    def generate_name(self):
        n = self.random_name()
        while n in self.in_use:
            n = self.random_name()
        self.in_use.add(n)
        self._name = n
