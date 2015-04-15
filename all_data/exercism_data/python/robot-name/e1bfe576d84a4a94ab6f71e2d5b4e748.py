from random import random

class Robot:
    def __init__(self):
        self.name = ""
        
        for i in range(2):
            self.name += chr(65 + int(random()*26))
        self.name += str(random())[2:5]
    
    def reset(self):
        self.__init__()
