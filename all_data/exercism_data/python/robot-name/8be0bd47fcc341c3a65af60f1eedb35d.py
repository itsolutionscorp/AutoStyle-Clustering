import random
import string


class Robot:

    names = set()

    def __init__(self):
        self.name = self.random_name()

    def random_name(self):
        while True:
            n = []

            for i in range(2):
                n.append(random.choice(string.ascii_uppercase))

            for i in range(3):
                n.append(random.choice(string.digits))

            name = ''.join(n)

            if name not in self.names:
                self.names.add(name)
                return name

    def reset(self):
        self.name = self.random_name()
