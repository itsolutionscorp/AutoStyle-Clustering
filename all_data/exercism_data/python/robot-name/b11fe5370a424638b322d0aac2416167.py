import random
import string


class Robot:
    __names = set()

    def __init__(self):
        self.reset()

    def reset(self):
        while True:
            up = string.ascii_uppercase
            di = string.digits
            name = "".join(random.choice(chars)
                           for chars in [up, up, di, di, di])
            if name not in self.__names:
                break
        self.__names.add(name)
        self.name = name
