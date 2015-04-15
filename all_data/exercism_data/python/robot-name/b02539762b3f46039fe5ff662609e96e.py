import random
import string

def _random_letters(n):
    return ''.join(random.choice(string.uppercase) for _ in range(n))

def _random_numbers(n):
    return ''.join(random.choice(string.digits) for _ in range(n))

class Robot(object):
    @staticmethod
    def _generate_name():
        return _random_letters(2) + _random_numbers(3)

    def __init__(self):
        self.reset()

    def reset(self):
        self.name = Robot._generate_name()
