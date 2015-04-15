class Luhn():
    @staticmethod
    def transform(i, x):
        return x if not i % 2 else (x * 2 if x < 5 else x * 2 - 9)

    @staticmethod
    def create(n):
        x = n * 10
        s = (10 - Luhn(x).checksum()) % 10
        return x + s

    def __init__(self, number):
        digits = [int(d) for d in reversed(str(number))]
        self.__addends = [self.transform(i, x) for i, x in enumerate(digits)]

    def addends(self):
        return self.__addends

    def checksum(self):
        return sum(self.__addends) % 10

    def is_valid(self):
        return self.checksum() == 0
