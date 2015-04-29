from itertools import product


ZERO = [
    " _ ",
    "| |",
    "|_|",
    "   "
]

ONE = [
    "   ",
    "  |",
    "  |",
    "   "
]


def grid():
    return 0


def number(pattern):
    if all(pattern[i][j] == ZERO[i][j] for i, j in product(range(4), range(3))):
        return '0'
    elif all(pattern[i][j] == ONE[i][j] for i, j in product(range(4), range(3))):
        return '1'
    return '?'
