SUBLIST = 0
SUPERLIST = 1
EQUAL = 2
UNEQUAL = 3

def check_lists(sublist, superlist):
    difference = len(superlist) - len(sublist)

    if difference == 0:
        if sublist == superlist:
            return EQUAL
    if difference > 0:
        if _is_sublist(sublist, superlist):
            return SUBLIST
    if difference < 0:
        if _is_sublist(superlist, sublist):
            return SUPERLIST
    return UNEQUAL

def _is_sublist(sublist, superlist):
    """sublist: Determine if a list is a sublist of another list.

    Note that an empty list is always contained in any other list.
    """
    if not sublist:
        return True
    return any(
        superlist[i:i+len(sublist)] == sublist
        for i in xrange(len(superlist))
    )
    return False

