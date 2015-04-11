from string import ascii_uppercase
import random

class Robot:

    _dead = ['default']

    def __init__(self):
        self.name = self.gen_name()

    def reset(self):
        self.__init__()

    def gen_name(self):
        name = 'default'
        while name in Robot._dead:
            name = random.sample(ascii_uppercase, 2) + (random.sample(range(9), 3))
            name = ''.join(map(str, name))
        Robot._dead.append(name)
        return name
