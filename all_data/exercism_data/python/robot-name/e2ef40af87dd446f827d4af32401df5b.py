import random
import string


class Robot(object):
    used_names = []

    def __init__(self):
        self.name = ''
        self.reset()

    @staticmethod
    def _generate_name():
        return ''.join([random.choice(string.ascii_uppercase), random.choice(string.ascii_uppercase),
                        random.choice(string.digits), random.choice(string.digits), random.choice(string.digits)])

    def reset(self):
        while not self.name or self.name in Robot.used_names:
            self.name = self._generate_name()
        Robot.used_names.append(self.name)
