"""Roman numerals."""

__all__ = ["RomanError", "to_roman", "RomanNumeral"]


class RomanError(Exception):
    """Raised when a number is not between 1 and 3999."""
    pass


_int_to_roman = (
    (1000, "M"),
    (900, "CM"),
    (500, "D"),
    (400, "CD"),
    (100, "C"),
    (90, "XC"),
    (50, "L"),
    (40, "XL"),
    (10, "X"),
    (9, "IX"),
    (5, "V"),
    (4, "IV"),
    (1, "I")
)


def to_roman(number):
    """Convert int to a Roman numeral.

    Args:
        number (int): An integer.
    Returns:
        str. A Roman numeral that corresponds to number.
    """
    if not (1 <= number <= 3999):
        raise RomanError("number out of range (must be 1..3999).")
    roman = []
    for integer, numeral in _int_to_roman:
        while number >= integer:
            roman.append(numeral)
            number -= integer
    return "".join(roman)


class RomanNumeral(int):
    """A Roman numeral."""

    def __init__(self, number):
        """Create a Roman numeral.

        Args:
            number (int): An integer (must be 1..3999).
        """
        if not (1 <= number <= 3999):
            raise RomanError("number out of range (must be 1..3999).")
        super(RomanNumeral, self).__init__(number)

    def __repr__(self):
        cls = self.__class__
        return "%s.%s(%r)" % (cls.__module__, cls.__name__, int(self))

    def __str__(self):
        return to_roman(self)
