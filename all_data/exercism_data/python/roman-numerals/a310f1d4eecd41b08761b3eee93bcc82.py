from collections import OrderedDict
NUMERALS = [
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
]

# sanity check
# in case someone updates the NUMERALS association list in the wrong order
NUMERALS = sorted(NUMERALS, reverse=True)

def numeral(arabic):
    n = arabic
    out = ""

    for symbol in NUMERALS:
        if n >= symbol[0]:
            num_s = n // symbol[0]
            out += symbol[1] * num_s
            n -= num_s * symbol[0]

    return out
