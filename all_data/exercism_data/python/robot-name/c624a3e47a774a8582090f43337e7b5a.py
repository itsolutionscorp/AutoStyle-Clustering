from random import choice, randint
from string import ascii_uppercase


# tests run in 1ms approx


class Robot():
    # class namespace #

    _names_in_use = set()
    _recentlyused = list()

    @classmethod
    def _getname(cls):
        c = choice(ascii_uppercase)
        cc = choice(ascii_uppercase)
        n = randint(0, 999)
        name = "{0}{1}{2:0>3}".format(c, cc, n)

        print("{} in {} : {}".format(name, cls._names_in_use, bool(name in cls._names_in_use)))

        if name in cls._names_in_use or name in cls._recentlyused:
            # try another time (might get recursive if a lot of instances exist.)
            name = cls._getname()
        # end of recursive search for available name.

        # Store it as in use.
        cls._names_in_use.add(name)

        if len(cls._recentlyused) >= (len(ascii_uppercase)**2*999) - len(cls._names_in_use):
            cls._recentlyused.pop(0)  # free one ID used long ago if recently used id are
            # more numerous than remaining combinations

        return name

    # instance namespace #

    def __init__(self):
        # get an id upon instanciation
        self.name = self._getname()

    def reset(self):
        # forget id was used before
        self._recentlyused.append(self.name)
        self._names_in_use.add(self.name)
        # get a new one immediatly
        self.name = self._getname()
