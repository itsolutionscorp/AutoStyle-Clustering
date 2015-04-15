from random import randint

class Robot(object):
    def __init__(self):
        self.name = (chr(randint(65,90)) +
                     chr(randint(65,90)) +
                     str(randint(0,9)) +
                     str(randint(0,9)) +
                     str(randint(0,9)))

    def reset(self):
        self.name = (chr(randint(65,90)) +
                     chr(randint(65,90)) +
                     str(randint(0,9)) +
                     str(randint(0,9)) +
                     str(randint(0,9)))
