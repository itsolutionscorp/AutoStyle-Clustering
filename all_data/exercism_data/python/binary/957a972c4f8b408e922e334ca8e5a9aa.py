"""A binary number."""


def to_decimal(binary):
    """Convert a binary representation to a decimal number.

    Args:
        binary (str): A binary representation.
    Returns:
        int. A decimal number that corresponds to ``binary``.
    """
    decimal = 0
    for digit in binary:
        decimal = decimal * 2 + (1 if digit == "1" else 0)
    return decimal


def from_decimal(decimal):
    """Convert a decimal number to a binary representation.

    Args:
        decimal (int): A decimal number.
    Returns:
        str. A binary representation that corresponds to ``decimal``.
    """
    binary = []
    quotient, remainder = divmod(decimal, 2)
    binary.append(str(remainder))
    while quotient > 0:
        quotient, remainder = divmod(quotient, 2)
        binary.append(str(remainder))
    binary.reverse()
    return "".join(binary)


class Binary(int):
    """A binary number."""

    def __new__(cls, binary):
        """
        Args:
            binary (str): A binary representation.
        """
        return super(Binary, cls).__new__(cls, to_decimal(binary))

    def __repr__(self):
        cls = self.__class__
        return "%s.%s(%r)" % (cls.__module__, cls.__name__, from_decimal(self))
