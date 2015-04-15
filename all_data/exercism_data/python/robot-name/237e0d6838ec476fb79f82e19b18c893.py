import random
import string


class NameGenerator(object):
    in_use = set()

    @classmethod
    def letters(cls, n):
        return "".join(random.choice(string.ascii_letters) for i in range(n))

    @classmethod
    def numbers(cls, n):
        return "".join(random.choice(string.digits) for i in range(n))

    @classmethod
    def random_name(cls):
        return "".join([cls.letters(2), cls.numbers(3)])

    def get_name(self):
        while True:
            n = self.random_name()
            if n not in self.in_use:
                break
        self.in_use.add(n)
        return n

    def release_name(self, n):
        if n in self.in_use:
            self.in_use.remove(n)


class Robot(object):

    name_generator = NameGenerator()

    def __init__(self):
        self._name = None

    @property
    def name(self):
        if self._name is None:
            self._name = self.name_generator.get_name()
        return self._name

    def reset(self):
        self.name_generator.release_name(self._name)
        self._name = None
