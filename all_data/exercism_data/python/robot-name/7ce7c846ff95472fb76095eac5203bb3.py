"""A robot with a randomly generated name."""

import random
import string


def rand_ascii_letter():
    """Return a random ASCII letter."""
    return random.choice(string.ascii_letters)


def randname():
    """Return a random robot name.

    A robot name consists of two ASCII letters and three digits.
    """
    name = "{:1s}{:1s}{:03d}".format(
        rand_ascii_letter(), rand_ascii_letter(), random.randint(0, 999))
    return name


# Robot names used so far.
names = set()


def genname():
    """Generate a random robot name not used so far."""
    name = randname()
    while name in names:
        name = randname()
    names.add(name)
    return name


class Robot(object):
    """A robot with a randomly generated name."""

    def __init__(self):
        self.name = genname()

    def __repr__(self):
        return "<{!s} {!s} name={!r}>".format(
            self.__class__.__name__, hex(id(self)), self.name)

    def __str__(self):
        return self.name

    def reset(self):
        """Reset the robot's name."""
        self.name = genname()
