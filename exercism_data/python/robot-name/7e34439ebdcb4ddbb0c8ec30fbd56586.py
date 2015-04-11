from random import choice
from string import ascii_uppercase as l, digits as d

class Robot(object):
    def __init__(self):
        self.reset()

    def reset(self):
        self.name = choice(l) + choice(l) + choice(d) + choice(d) + choice(d)
