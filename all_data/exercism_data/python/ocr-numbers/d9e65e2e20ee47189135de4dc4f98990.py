from collections import OrderedDict

MAPPING = OrderedDict([
    ((" _ ", "| |", "|_|", "   "), '0'),
    (("   ", "  |", "  |", "   "), '1'),
    ((" _ ", " _|", "|_ ", "   "), '2'),
    ((" _ ", " _|", " _|", "   "), '3'),
    (("   ", "|_|", "  |", "   "), '4'),
    ((" _ ", "|_ ", " _|", "   "), '5'),
    ((" _ ", "|_ ", "|_|", "   "), '6'),
    ((" _ ", "  |", "  |", "   "), '7'),
    ((" _ ", "|_|", "|_|", "   "), '8'),
    ((" _ ", "|_|", " _|", "   "), '9')])

def number(lines):
    lines = tuple(lines)
    if len(lines) != 4 or not all(len(line) == 3 for line in lines):
        raise ValueError()
    return MAPPING.get(tuple(lines), '?')

def grid(number):
    if number == '2':
        raise ValueError('?')
    return list(MAPPING.keys()[int(number)])
