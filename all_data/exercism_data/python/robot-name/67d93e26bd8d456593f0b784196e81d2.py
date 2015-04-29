from random import choice
from string import ascii_uppercase, digits


class Robot:
    def __init__(self):
        self.name = ''.join(choice(ascii_uppercase) for _ in range(2)) + \
                    ''.join(choice(digits) for _ in range(3))
