#William Morris
#exercism.io
#robot.py
import string
import random

class Robot:

    def __init__(self):
        
        self.name = '{}{}{}{}{}'.format(random.choice(string.ascii_uppercase),
                                        random.choice(string.ascii_uppercase),
                                        random.choice(string.digits),
                                        random.choice(string.digits),
                                        random.choice(string.digits))
                                        

    def reset(self):
        self.__init__()
