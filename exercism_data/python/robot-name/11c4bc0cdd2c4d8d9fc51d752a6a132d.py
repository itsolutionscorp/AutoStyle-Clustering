from random import randint

class Robot(object):
    def __init__(self):
        self.name = name_generator()
    def reset(self):
        self.name = name_generator()

def name_generator():
    name = chr(randint(ord('A'),ord('Z'))) + chr(randint(ord('A'),ord('Z'))) + str(randint(0,9)) + str(randint(0,9)) + str(randint(0,9))
    return name
