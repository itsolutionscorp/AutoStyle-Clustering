import random
from uuid import uuid4
from string import ascii_uppercase, digits

class Robot():
    def __init__(self):
        self.name = self.generate_name()

    def reset(self):
        self.name = self.generate_name()

    @staticmethod
    def generate_name():
        random.seed(uuid4())
        return ''.join([random.choice(ascii_uppercase) for i in range(2)]+[random.choice(digits) for i in range(3)])
