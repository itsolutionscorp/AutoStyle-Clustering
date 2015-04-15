import random

class Robot:
    
    def __init__(self):
        self.name = Robot.generateName()

    def reset(self):
        self.name = Robot.generateName()
    
    @staticmethod
    def generateName():
        random.seed()
        chars = []
        for i in range(2):
            chars.append(chr(random.randint(65, 90)))
        for i in range(3):
            chars.append(str(random.randint(0, 9)))
        return ''.join(chars)
