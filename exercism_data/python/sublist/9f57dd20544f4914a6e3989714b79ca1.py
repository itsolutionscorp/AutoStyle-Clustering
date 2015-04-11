EQUAL = 0
SUBLIST = 1
SUPERLIST = 2
UNEQUAL = 3

def is_sublist(l1, l2):
    result = False
    sz1 = len(l1)
    sz2 = len(l2)

    if sz1 > sz2:
        result = False
    elif sz1 == 0:
        result = True
    else:
        for i in range(sz2-sz1+1):
            if l1 == l2[i:i+sz1]:
                result = True
                break

    return result

def check_lists(l1, l2):
    result = EQUAL
    sz1 = len(l1)
    sz2 = len(l2)

    if sz1 < sz2:
        result = SUBLIST
        if not is_sublist(l1, l2):
            result = UNEQUAL
    if sz1 > sz2:
        result = SUPERLIST
        if not is_sublist(l2, l1):
            result = UNEQUAL
    else:
        if not is_sublist(l1, l2):
            result = UNEQUAL

    return result
