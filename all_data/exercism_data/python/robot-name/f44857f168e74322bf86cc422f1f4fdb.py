import string
import random

class Robot(object):
    def __init__(self):
        self.assigned_names = set()
        self.reset()

    def reset(self):
        while True:
            self.name  = random.choice(string.uppercase)
            self.name += random.choice(string.uppercase)
            self.name += random.choice(string.digits)
            self.name += random.choice(string.digits)
            self.name += random.choice(string.digits)
            if self.name not in self.assigned_names:
                self.assigned_names.add(self.name)
                break
