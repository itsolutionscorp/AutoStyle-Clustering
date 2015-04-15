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

CHARS = { '0': ZERO, '1': ONE }

def number(grid):
    if len(grid) != 4 or not all(len(g)==3 for g in grid): raise ValueError
    return ([ c for c,g in CHARS.items() if g == grid ] + ['?'])[0]

def grid(number):
    if number not in CHARS: raise ValueError
    return CHARS[number]
