"""An octal number."""


def to_int(digits, base=10):
    """Convert an integer representation in the given base to an integer.

    :param digits: an integer representation
    :type digits: str
    :param base: a number base (2 <= base <= 10)
    :type base: int
    :rtype: int
    """
    zero = ord('0')
    valid_digits = [chr(n) for n in xrange(zero, zero + base)]
    number = 0
    for digit in digits:
        if digit not in valid_digits:
            raise ValueError(digit)
        number = number * base + (ord(digit) - zero)
    return number


def from_int(number, base=10):
    """Convert an integer to an integer representation in the given base.

    :param number: an integer
    :type number: int
    :param base: a number base (2 <= base <= 10)
    :type base: int
    :rtype: str
    """
    digits = []
    quotient, remainder = divmod(number, base)
    digits.append(str(remainder))
    while quotient > 0:
        quotient, remainder = divmod(quotient, base)
        digits.append(str(remainder))
    digits.reverse()
    return "".join(digits)


class Octal(int):
    """An octal number."""

    def __new__(cls, octal):
        """Create an integer from an octal representation.

        :param octal: an octal representation
        :type octal: str
        """
        try:
            return super(Octal, cls).__new__(cls, to_int(octal, base=8))
        except ValueError as err:
            raise ValueError("Invalid octal digit: {}".format(err))

    def __repr__(self):
        cls = self.__class__
        return "{!s}.{!s}({!r})".format(
            cls.__module__, cls.__name__, from_int(self, base=8))

    def to_decimal(self):
        """Return the integer."""
        return int(self)
