import random
import string

class Robot:
    def __init__(self):
        self.name = self.reset();

    def reset(self):
        self.name = self._generate_random('string', 2)
        self.name += self._generate_random('digit', 3)
        return self.name


    def _generate_random(self, kind, length):
        if kind is 'string':
            generator = string.ascii_uppercase
        else:
            generator = string.digits
        return ''.join(random.choice(generator) for _ in range(0, length))
