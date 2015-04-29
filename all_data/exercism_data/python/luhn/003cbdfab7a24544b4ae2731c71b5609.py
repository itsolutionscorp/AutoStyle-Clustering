"""The Luhn forumla."""


class Luhn(object):
    """The Luhn forumla."""

    def __init__(self, number):
        self.digits = [int(digit) for digit in str(number)]

    def is_valid(self):
        """Return true if the number is valid per the Luhn formula."""
        return self.checksum() == 0

    @staticmethod
    def create(partial):
        """Append a check digit to make a number valid per the Luhn formula."""
        shifted = partial * 10
        check = (10 - Luhn(shifted).checksum()) % 10
        return shifted + check

    def addends(self):
        _addends = list(self._addends_reversed())
        _addends.reverse()
        return _addends

    def checksum(self):
        return sum(self._addends_reversed()) % 10

    def _addends_reversed(self):
        for i, digit in enumerate(reversed(self.digits)):
            if i % 2 != 0:
                double = digit * 2
                yield double - 9 if double >= 10 else double
            else:
                yield digit
