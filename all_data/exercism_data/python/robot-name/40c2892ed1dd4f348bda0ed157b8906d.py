from string import ascii_uppercase as AU
from random import randint, choice

_names = set("{0}{1}{2:0>3}".format(c,cc,n)
            for c in AU
            for cc in AU
            for n in range(999))

def random_name():
    return "{0}{1}{2:0>3}".format(choice(AU),choice(AU),randint(0,999))


class Robot():
    def __init__(self):
        self._name = None

    @property
    def name(self):
        if self._name:
            return self._name
        else:
            self._name = min(_names)
            _names.remove(self._name)
            return self.name

    def reset(self):
        self._name = None
