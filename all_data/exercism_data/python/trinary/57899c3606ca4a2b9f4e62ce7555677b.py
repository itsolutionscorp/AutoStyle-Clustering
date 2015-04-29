"""Trinary numbers."""


def trinary(digits):
    """Convert a trinary representation to its equivalent integer."""
    try:
        return to_int(digits, base=3)
    except ValueError:
        return 0


def to_int(digits, base=10):
    """Convert an integer representation in the given base to an integer.

    :param digits: a nonnegative integer representation
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
