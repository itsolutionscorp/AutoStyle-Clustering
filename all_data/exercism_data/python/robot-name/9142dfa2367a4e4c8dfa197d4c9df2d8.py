import itertools
import random
from string import ascii_uppercase as _up, digits as _di


class Robot:
    __names = set(itertools.product(_up, _up, _di, _di, _di))

    def __init__(self):
        self.reset()

    def reset(self):
        self.name = "".join(self.__names.pop())
