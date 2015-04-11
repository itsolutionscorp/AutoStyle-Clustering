EQUAL = "Equals"
UNEQUAL = "Unequals"
SUBLIST = "Sublist"
SUPERLIST = "Superlist"


# series
def slices(in_iterable, n):
    """returns all possible slices converted to lists of ints."""
    if not 0 < n <= len(in_iterable):
        raise ValueError('Length of slice is wrong')
    return (list(in_iterable[i:i+n]) for i in range(len(in_iterable)-n+1))


def check_lists(l1, l2):
    if l1 == l2 == []:
        return EQUAL
    if l1 == []:
        return SUBLIST
    if l2 == []:
        return SUPERLIST
    if len(l1) == len(l2):
        if l1 == l2:
            return EQUAL
        else:
            return UNEQUAL
    else:
        if len(l1)>len(l2) and ( l2 in slices(l1,len(l2)) ):
            return SUPERLIST
        if len(l1)<len(l2) and ( l1 in slices(l2,len(l1)) ):
            return SUBLIST
    return UNEQUAL
