SUBLIST = 1
SUPERLIST = 2
EQUAL = 3
UNEQUAL = 4

def check_lists(l1, l2):
    if l1 == l2:
        return EQUAL

    if len(l1) < len(l2):
        # check for SUBLIST
        for i in range(len(l2) - len(l1) + 1):
            if l2[ i : i + len(l1) ] == l1:
                return SUBLIST

    elif len(l1) > len(l2):
        # check for SUPERLIST
        for i in range(len(l1) - len(l2) + 1):
            if l1[ i : i + len(l2) ] == l2:
                return SUPERLIST

    return UNEQUAL
