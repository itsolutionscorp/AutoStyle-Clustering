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
        self.number = number

    def addends(self):
        addends_reversed = list(digits(self.number))
        addends_reversed[1::2] = [(2 * d - 1) % 9 + 1
                                  for d in addends_reversed[1::2]]
        return list(reversed(addends_reversed))

    def checksum(self):
        return sum(self.addends()) % 10

    def is_valid(self):
        return self.checksum() == 0

    @staticmethod
    def create(number):
        luhn_zero_sum = Luhn(number*10)

        return luhn_zero_sum.number + (10 - luhn_zero_sum.checksum()) % 10
