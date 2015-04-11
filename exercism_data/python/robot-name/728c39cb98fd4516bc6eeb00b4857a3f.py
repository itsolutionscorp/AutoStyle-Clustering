from string import ascii_uppercase
import random

class Robot:

    def __init__(self):
        random.seed()
        name = random.sample(ascii_uppercase, 2) + (random.sample(range(9), 3))
        self.name = ''.join(map(str, name))

    def reset(self):
        self.__init__()
