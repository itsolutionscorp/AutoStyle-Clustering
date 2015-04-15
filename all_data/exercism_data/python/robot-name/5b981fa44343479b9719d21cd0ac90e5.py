from random import choice
from string import ascii_letters, digits

class Robot:
    used_names = set()

    def __init__(self):
        self._get_new_name()

    def reset(self):
        old_name = self.name
        self._get_new_name()
        Robot.release(old_name)

    def _get_new_name(self):
        self.name = Robot.pick()

    @staticmethod
    def pick():
        while True:
            name = Robot._random_name()
            if Robot.acquire(name):
                return name

    @staticmethod
    def acquire(name):
        if name in Robot.used_names:
            return False

        Robot.used_names.add(name)
        return True

    @staticmethod
    def release(name):
        Robot.used_names.remove(name)

    @staticmethod
    def _random_name():
        return ( choice(ascii_letters)
                 + choice(ascii_letters)
                 + choice(digits)
                 + choice(digits)
                 + choice(digits) )

    pass
