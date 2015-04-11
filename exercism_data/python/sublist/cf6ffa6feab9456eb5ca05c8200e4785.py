SUBLIST, SUPERLIST, EQUAL, UNEQUAL = range(4)

def check_lists(l1, l2):
    if l1 == l2:
        return EQUAL
    elif is_sublist(l1, l2):
        return SUBLIST
    elif is_sublist(l2, l1):
        return SUPERLIST

    return UNEQUAL

def is_sublist(l1, l2):
    return len(l2) >= len(l1) and any(l2[i:i + len(l1)] == l1 for i in range(len(l2) - len(l1) + 1))
