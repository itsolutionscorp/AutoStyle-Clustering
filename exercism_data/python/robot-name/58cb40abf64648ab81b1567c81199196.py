from random import choice
from string import ascii_uppercase

class Robot(object):
    
    def __init__(self):
        self.name = self.create_name()
    
    def create_name(self):
        char = [choice(ascii_uppercase) for x in range(0,2)]
        num = [str(choice(range(0,9))) for x in range(0,3)]
        return ''.join(char+num)
    
    def reset(self):
        return setattr(self, 'name', 'AA000')
