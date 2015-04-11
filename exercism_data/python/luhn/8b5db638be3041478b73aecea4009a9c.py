from __future__ import division

def digits(number):
    """
    digits(int) -> yields ints

    Returns the digits of the given number from right to left.
    """

    while number > 0:
        yield number%10
        number //= 10


class Luhn:
    def __init__(self, number):
        self._number = number
        self._addends = None
        self._checksum = None

    def addends(self):
        if self._addends is None:
            self._addends = []
            for i, d in enumerate(digits(self._number)):
                if i % 2 != 0:
                    d = (2 * d - 1) % 9 + 1

                self._addends.append(d)
            self._addends.reverse()

        return self._addends

    def checksum(self):
        if self._checksum is None:
            self._checksum = sum(self.addends()) % 10

        return self._checksum

    def is_valid(self):
        return self.checksum() == 0

    @staticmethod
    def create(number):
        luhn = Luhn(number*10)

        return number*10 + (10 - luhn.checksum()) % 10
