UNEQUAL, EQUAL, SUBLIST, SUPERLIST = range(4)


def _cmp_sublist(big, Nb, small, Ns, retval):
    for i in range(Nb - Ns + 1):
        if big[i:i + Ns] == small:
            return retval
    return UNEQUAL


def check_lists(l1, l2):
    LEN1, LEN2 = map(len, (l1, l2))
    if LEN1 > LEN2:
        return _cmp_sublist(l1, LEN1, l2, LEN2, SUPERLIST)
    elif LEN1 < LEN2:
        return _cmp_sublist(l2, LEN2, l1, LEN1, SUBLIST)
    else:
        if l1 == l2:
            return EQUAL
    return UNEQUAL
