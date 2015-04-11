import string
from random import choice
from itertools import starmap, repeat


class Robot(object):

    def __init__(self):
        self.name = ''
        self.reset()

    def _random_name(self):
        choices = ([string.ascii_uppercase] * 2) + ([string.digits] * 3)
        return ''.join(map(choice, choices))

    def reset(self):
        names = starmap(self._random_name, repeat([]))
        self.name = (name for name in names if not name == self.name).next()
