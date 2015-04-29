"""Hexadecimal numbers."""

import string


def hexa(digits):
    """Convert an hexadecimal integer representation to an integer.

    :param digits: a nonnegative hexadecimal integer representation
    :type digits: str
    """
    return to_int(digits, base=16)


def to_int(digits, base=10):
    """Convert an integer representation in the given base to an integer.

    :param digits: a nonnegative integer representation
    :type digits: str
    :param base: a number base (2 <= base <= 36)
    :type base: int
    :rtype: int
    """
    assert 2 <= base <= 36

    valid_digits = set(string.digits[:base])
    if base > 10:
        valid_digits.update(string.ascii_lowercase[:(base - 10)])

    number = 0
    for digit in digits.lower():
        if digit not in valid_digits:
            raise ValueError(digit)
        if digit.isdigit():
            number = number * base + (ord(digit) - _ORD_0)
        else:
            number = number * base + (10 + (ord(digit) - _ORD_a))
    return number


def from_int(number, base=10):
    """Convert an integer to an integer representation in the given base.

    :param number: a nonnegative integer
    :type number: int
    :param base: a number base (2 <= base <= 36)
    :type base: int
    :rtype: str
    """
    assert 2 <= base <= 36

    digits = []
    quotient = number
    while True:
        quotient, remainder = divmod(quotient, base)
        if remainder < 10:
            digits.append(string.digits[remainder])
        else:
            digits.append(string.ascii_lowercase[remainder - 10])
        if quotient == 0:
            break
    digits.reverse()
    return "".join(digits)


_ORD_0, _ORD_a = ord("0"), ord("a")
