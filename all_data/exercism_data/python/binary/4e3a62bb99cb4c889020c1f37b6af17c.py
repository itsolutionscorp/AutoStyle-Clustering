'''exer binary'''


def parse_binary(bstr):
    '''parse binary string representation and return int value or
    raise ValueError'''

    # move least significant digit to the left
    lsd_rep = [int(x) for x in bstr if x in('0', '1')]  # catches non-numeric
    lsd_rep.reverse()
    # look for stripped elements or empty strings in input
    if len(lsd_rep) != len(bstr) or len(lsd_rep) == 0:
        raise ValueError

    # set starting exponent and integer value to return
    exponent = 0
    int_val = 0

    for bdigit in lsd_rep:
        int_val += bdigit * 2 ** exponent
        exponent += 1

    return int_val
