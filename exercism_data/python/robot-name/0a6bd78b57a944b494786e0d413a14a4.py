__author__ = 'angelo'

from string import ascii_uppercase
from random import choice, randint

class Robot:

    def __init__(self):
        self.name = "{0}{1}{2}".format(choice(ascii_uppercase), choice(ascii_uppercase), str(randint(100, 999)))

    def reset(self):
        self.__init__()
