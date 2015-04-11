UNEQUAL = 0
EQUAL = 1
SUBLIST = 2
SUPERLIST = 3

def is_sublist(A, B):
    for i in range(len(B) - len(A) + 1):
        if B[i:i+len(A)] == A:
            return True
    return False

def check_lists(l1, l2):
    if l1 == l2:
        return EQUAL
    elif is_sublist(l1, l2):
        return SUBLIST
    elif is_sublist(l2, l1):
        return SUPERLIST
    else:
        return UNEQUAL
