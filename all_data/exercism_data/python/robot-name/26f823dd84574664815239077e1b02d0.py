from random import shuffle
from string import ascii_uppercase


# tests run in 5ms approx


class Robot():

    # class namespace #

    _names = list("{0}{1}{2:0>3}".format(c, cc, n)
                  for c in ascii_uppercase
                  for cc in ascii_uppercase
                  for n in range(999))  # queue of all ids
    # make that a shuffled queue (called once per class so not too time consuming)
    shuffle(_names)

    # instance namespace #

    def __init__(self):
        # get an id upon instanciation
        self._getname()

    @property  # see the docs if this seems odd, @property makes computed attributes easy
    def name(self):
        return self._name

    def reset(self):
        # put id back in the pool of available ids (at end of the queue)
        self._names.append(self._name)
        # get a new one immediatly
        self._getname()

    def _getname(self):
        # separate (and private) method to improve readability
        # and to allow overriding in a potential subclass
        self._name = self._names.pop(0)  # update id and remove it from the queue through pop()
