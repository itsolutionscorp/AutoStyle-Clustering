import string
import random


class Robot(object):
    def __init__(self):
        self.reset()

    def _generate_name(self):
        prefix = [random.choice(string.ascii_uppercase) for __ in range(2)]
        suffix = [random.choice(string.digits) for __ in range(3)]
        return ''.join(prefix + suffix)

    def reset(self):
        self.name = self._generate_name()
