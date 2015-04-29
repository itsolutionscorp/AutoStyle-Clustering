def random_name():
    from string import ascii_uppercase as AU
    from random import randint, choice
    return "{0}{1}{2:0>3}".format(choice(AU),choice(AU),randint(0,999))


class Robot():
    def __init__(self):
        self._name = None

    @property
    def name(self):
        if self._name:
            return self._name
        else:
            self._name = random_name()
            return self.name

    def reset(self):
        self._name = None
