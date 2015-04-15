'''exer roman numerals'''

def numeral(val):
    '''convert the integer value, val to it's roman counter part'''

    to_roman = {
        1:'I',
        4:'IV',
        5:'V',
        9:'IX',
        10:'X',
        40:'XL',
        50:'L',
        90:'XC',
        100:'C',
        400:'CD',
        500:'D',
        900:'CM',
        1000:'M',
    }
    qed = ''
    keys = to_roman.keys()  # no guaranteed order
    keys.sort()             # want from highest val to lowest, so sort
    keys.reverse()          # then reverse
    for key in keys:        # now start hacking away at val
        while val >= key:
            qed += to_roman[key]    # quad erat demonstrandum
            val -= key              # decr val by key and repeat

    return qed              # quite easily done.
