from collections import OrderedDict
NUMERALS = {
    1: 'I',
    4: 'IV',
    5: 'V',
    9: 'IX',
    10: 'X',
    40: 'XL',
    50: 'L',
    90: 'XC',
    100: 'C',
    400: 'CD',
    500: 'D',
    900: 'CM',
    1000: 'M',
}
NUMERALS = OrderedDict(sorted(NUMERALS.items(),
                              key=lambda t: t[0],
                              reverse=True)) 

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
