SUBLIST = 1
SUPERLIST = 2
EQUAL = 3
UNEQUAL = 4


def is_sublist(A, B):
    a, b = len(A), len(B)

    for i in range(0, b - a + 1):
        if B[i:i+a] == A:
            return True

    return False


def check_lists(A, B):
    if A == B:
        return EQUAL

    elif is_sublist(A, B):
        return SUBLIST

    elif is_sublist(B, A):
        return SUPERLIST

    return UNEQUAL
