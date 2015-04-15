import random

class Robot:

    def __init__(self):
        self.name = ''
        self.reset()

    def reset(self):
        tmp = ''

        for x in range(5):
            if x < 2:
                tmp += chr(random.randint(65, 90))
            else:
                tmp += str(random.randint(0,9))

        self.name = tmp
