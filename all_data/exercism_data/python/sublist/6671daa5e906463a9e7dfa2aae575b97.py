SUBLIST, SUPERLIST, EQUAL, UNEQUAL = (1, 2, 3, 4)


def check_lists(a, b):
    if len(a) == len(b):
        if a == b:
            return EQUAL
        else:
            return UNEQUAL
    res = SUBLIST
    if len(a) > len(b):
        a, b = b, a
        res = SUPERLIST
    if len(a) == 0:
        return res
    while(a[0] in b):
        i = b.index(a[0])
        if a == b[i:i+len(a)]:
            return res
        b = b[i+1:]
    return UNEQUAL
