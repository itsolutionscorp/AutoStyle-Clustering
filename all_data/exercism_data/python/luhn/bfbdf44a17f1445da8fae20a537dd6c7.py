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

    @staticmethod
    def mangle(index, digit):
        """
        mangle_pair(int, int) -> int

        If the first parameter is odd, mangle the second and return it.
        Return the second parameter unchanged otherwise.
        """

        if index % 2 != 0:
            return (2 * digit - 1) % 9 + 1
        else:
            return digit

    def addends(self):
        addends_reversed = [Luhn.mangle(i, d) 
                            for i, d in enumerate(digits(self.number))]
        return list(reversed(addends_reversed))

    def checksum(self):
        return sum(self.addends()) % 10

    def is_valid(self):
        return self.checksum() == 0

    @staticmethod
    def create(number):
        luhn_zero_sum = Luhn(number*10)

        return luhn_zero_sum.number + (10 - luhn_zero_sum.checksum()) % 10
