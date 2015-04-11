from __future__ import division

def digits(number):
    """
    digits(int) -> list of ints

    Returns the digits of the given number from left to right.
    """

    return [int(a) for a in str(abs(number))]


class Luhn:
    def __init__(self, number, contains_checksum=True):
        self.number = number

        if not contains_checksum:
            self.number *= 10

    def addends(self):
        addends = digits(self.number)
        addends[-2::-2] = [(2 * d - 1) % 9 + 1 for d in addends[-2::-2]]
        return addends

    def checksum(self):
        return sum(self.addends()) % 10

    def is_valid(self):
        return self.checksum() == 0

    def fix_checksum(self):
        self.number -= self.number % 10
        self.number += (10 - self.checksum()) % 10

    def __eq__(self, other):
        return self.number == other

    @staticmethod
    def create(number):
        luhn = Luhn(number, contains_checksum=False)
        luhn.fix_checksum()
        return luhn
