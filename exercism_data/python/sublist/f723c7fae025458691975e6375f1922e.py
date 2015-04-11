SUBLIST = 'SUBLIST'
SUPERLIST = 'SUPERLIST'
EQUAL = 'EQUAL'
UNEQUAL = 'UNEQUAL'

def check_lists(l1, l2):
    if (l1 == l2):
        return EQUAL
    if (__is_sublist(l1, l2)):
        return SUBLIST
    if (__is_sublist(l2, l1)):
        return SUPERLIST
    return UNEQUAL

def __is_sublist(l1, l2):
    if not l1:
        return True
    for val in l1:
        if val not in l2:
            return False
    return True
