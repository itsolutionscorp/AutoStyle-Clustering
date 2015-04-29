import random
from string import ascii_uppercase as letters

class Robot(object):
    def __init__(self):
        self.set_name()

    def set_name(self):
        alphabet = list(letters)
        random.shuffle(alphabet)
        self.name = ''.join(alphabet[:2]) + str(random.randrange(100,1000))
            
    def reset(self):
        self.set_name()
