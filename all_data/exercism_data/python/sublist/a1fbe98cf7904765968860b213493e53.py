SUBLIST = 0b01
SUPERLIST = 0b10
EQUAL = 0b11
UNEQUAL = 0b00

def check_lists(reference, compared_to):
    r = len(reference)
    c = len(compared_to)

    rs = ':'.join(map(repr, reference))
    cs = ':'.join(map(repr, compared_to))

    if r >= c and cs in rs:
        return EQUAL if r==c else SUPERLIST

    return SUBLIST if rs in cs else UNEQUAL
    
