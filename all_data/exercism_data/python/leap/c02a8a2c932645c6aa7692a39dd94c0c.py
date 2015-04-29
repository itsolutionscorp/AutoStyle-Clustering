"""A year."""


def divisible(x, y):
    """Return true is the 1st argument is divisible by the 2nd argument."""
    return x % y == 0


class Year(object):
    """A year class."""

    def __init__(self, year):
        self.year = year

    def __repr__(self):
        return "{!s}({!r})".format(self.__class__.__name__, self.year)

    def __str__(self):
        return str(self.year)

    def is_leap_year(self):
        """Return true if the year is a leap year.

        A year is a leap year if 1) it is divisible by 4 and 2) it is not
        divisible by 100 or it is divisible by 400.
        """
        return (divisible(self.year, 4) and
                ((not divisible(self.year, 100)) or divisible(self.year, 400)))
