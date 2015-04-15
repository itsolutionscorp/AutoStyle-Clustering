import re

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

def grid(c):
    ret = []

    if c == '0':
        ret = ZERO
    elif c == '1':
        ret = ONE
    else:
        raise ValueError('\'%s\' is not valid.' % c)

    return ret

def number(arr):
    ret = '?'

    if len(arr) != 4:
        raise ValueError('Incorrect number of rows.')

    for s in arr:
        if len(s) != 3:
            raise ValueError('Invalid line length.')

        m = re.match('[ \|_]*', s)
        if not m:
            raise ValueError('Invalid character.')

    if arr == ZERO:
        ret = '0'
    elif arr == ONE:
        ret = '1'

    return ret
