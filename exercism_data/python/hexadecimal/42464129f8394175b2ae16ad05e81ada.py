'''exer hexadecimal'''


def hexa(hstr):
    '''given a potential hexadecimal str, hstr, convert to integer or
    ValueError'''

    hvals = '0123456789abcdef'
    # put in lsd format
    hlsd = hstr[::-1].lower()

    exponent = 0
    int_val = 0
    for hval in hlsd:
        if hval not in hvals:
            raise ValueError
        int_val += hvals.index(hval) * 16 ** exponent
        exponent += 1

    return int_val
