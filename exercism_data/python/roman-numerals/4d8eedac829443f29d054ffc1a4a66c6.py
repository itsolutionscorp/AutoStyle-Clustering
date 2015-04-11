from collections import OrderedDict


ROMAN_DIGITS = OrderedDict(((1000, 'M'), (900, 'CM'), (500, 'D'),
                            (400, 'CD'), (100, 'C'), (90, 'XC'),
                            (50, 'L'), (40, 'XL'), (10, 'X'),
                            (9, 'IX'), (5, 'V'), (4, 'IV'), (1, 'I')
                            ))


class RomanNumeral(object):

    """A number in roman numerals."""

    def __init__(self, number):
        """Create new roman numeral from decimal number."""
        self.number = number

    def __str__(self):
        """Return a string representation of this roman numeral."""
        roman = ""
        number = self.number
        for value, digit in ROMAN_DIGITS.items():
            while value <= number:
                roman += digit
                number -= value
        return roman
