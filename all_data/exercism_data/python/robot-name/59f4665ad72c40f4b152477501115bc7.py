import itertools
import random
from string import ascii_uppercase as _up, digits as _di


class Robot:
    __names = set(itertools.product(up, up, di, di, di))

    def __init__(self):
        self.reset()

    def reset(self):
        self.name = self.__names.pop()
