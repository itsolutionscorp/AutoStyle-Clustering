from string import ascii_uppercase as AU


_names = set("{0}{1}{2:0>3}".format(c,cc,n)
            for c in AU
            for cc in AU
            for n in range(999))


class Robot():
    def __init__(self):
        self.reset()

    @property
    def name(self):
        if not self._name:
            self._name = min(_names)
            _names.remove(self._name)
        return self._name

    def reset(self):
        self._name = None
        self.name
