from string import hexdigits as HEX_DIGITS


def hexa(hex):
    return sum(HEX_DIGITS.index(char) * 16 ** index for index, char in enumerate(reversed(hex.lower())))
