SUBLIST, SUPERLIST, EQUAL, UNEQUAL = range(4)

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

    sublist_len = len(sublist)

    for start in _start_candidates(sublist, superlist):
        if superlist[start: start + sublist_len] == sublist:
            return True
    return False

def _start_candidates(sublist, superlist):
    first_char = sublist[0]
    superlist_len = len(superlist)
    sublist_len = len(sublist)

    cursor = 0
    while cursor < superlist_len:
        try:
            cursor = superlist.index(first_char, cursor)
        except ValueError:
            return
        if superlist_len - cursor >= sublist_len:
            yield cursor
        cursor += 1
