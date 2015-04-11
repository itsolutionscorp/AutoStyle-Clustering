import string
from random import randint
from random import choice
class Robot:
    """ Robot builds random names for robots """

    def __init__(self):
        self.reset()

    def reset(self):
        nameNumber = "" + str(randint(0,9)) + str(randint(0,9)) + str(randint(0,9))
        nameLetters = "" + choice(string.ascii_uppercase) + choice(string.ascii_uppercase)
        self.name = "{}{}".format(nameLetters, nameNumber)
