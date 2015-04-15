__author__ = 'emiller42'

import random
import string


class Robot:

    def __init__(self):
        Robot.reset(self)

    def reset(self):
        self.name = _generate_name()


def _generate_name():
    name = ""
    for i in range(0, 2):
        name += random.choice(string.ascii_uppercase)
    for i in range(0, 3):
        name += random.choice(string.digits)
    return name
