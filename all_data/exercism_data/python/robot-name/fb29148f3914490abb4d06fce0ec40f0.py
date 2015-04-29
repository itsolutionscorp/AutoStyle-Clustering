from random import randint


class Robot:
    def __init__(self):
        self.name = self._generate_name()

    def reset(self):
        self.name = self._generate_name()

    @classmethod
    def _generate_name(cls):
        return (cls.__random_uppercase_letter()
                + cls.__random_uppercase_letter()
                + str(randint(100, 999)))

    @staticmethod
    def __random_uppercase_letter():
        return chr(randint(ord('A'), ord('Z')))
