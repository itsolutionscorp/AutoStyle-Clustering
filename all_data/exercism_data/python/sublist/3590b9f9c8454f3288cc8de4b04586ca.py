SUBLIST = 0
SUPERLIST = 1
EQUAL = 2
UNEQUAL = 3

def check_lists(l1, l2):
    if len(l1) == len(l2):
        if l1 == l2:
            return EQUAL
    elif len(l1) < len(l2):
        for i in range(len(l2) - len(l1) + 1):
            if l1 == l2[i:i+len(l1)]:
                return SUBLIST
    else:
        for i in range(len(l1) - len(l2) + 1):
            if l2 == l1[i:i+len(l2)]:
                return SUPERLIST
    return UNEQUAL
