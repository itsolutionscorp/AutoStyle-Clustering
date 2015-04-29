import random
import string

names = set()

class Robot:
    def __init__(self):
        self.name = ''
        self.reset()
        random.seed()

    def reset(self):
        while(self.name == '' or self.name in names):
            self.name = ''.join([Robot.randomAlpha(), Robot.randomAlpha(), Robot.randomDigits(3)])
        names.add(self.name)

    @classmethod
    def randomAlpha(cls):
        return random.choice(string.ascii_uppercase)

    @classmethod
    def randomDigits(cls, num):
        return str(random.randint(0, 10**num - 1)).zfill(num)
