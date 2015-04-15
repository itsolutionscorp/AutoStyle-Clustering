from random import choice, randint
from string import ascii_uppercase


# tests run in 1ms approx


class Robot():

    # class namespace #

    _names_in_use = set()

    @classmethod
    def _getname(cls):
        c = choice(ascii_uppercase)
        cc = choice(ascii_uppercase)
        n = randint(0, 999)
        name = "{0}{1}{2:0>3}".format(c, cc, n)

        if name in cls._names_in_use:
            # try another time (might get recursive if a lot of instances exist.)
            name = cls._getname()
        # end of recursive search for available name.

        # Store it as in use.
        cls._names_in_use.add(name)
        return name

    # instance namespace #

    def __init__(self):
        # get an id upon instanciation
        self.name = self._getname()

    def reset(self):
        # forget id was used before
        self._names_in_use.remove(self.name)
        # get a new one immediatly
        self.name = self._getname()
