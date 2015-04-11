from collections import OrderedDict
NUMERALS = OrderedDict([
    (1000, 'M'),
    ( 900, 'CM'),
    ( 500, 'D'),
    ( 400, 'CD'),
    ( 100, 'C'),
    (  90, 'XC'),
    (  50, 'L'),
    (  40, 'XL'),
    (  10, 'X'),
    (   9, 'IX'),
    (   5, 'V'),
    (   4, 'IV'),
    (   1, 'I'),
])

def numeral(arabic):
    n = arabic
    out = ""
    symbols = NUMERALS.keys()

    for s in symbols:
        if n >= s:
            num_s = n // s
            out += NUMERALS[s] * num_s
            n -= num_s * s

    return out
