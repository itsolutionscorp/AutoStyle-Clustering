class SUBLIST:
    pass

class SUPERLIST:
    pass

class EQUAL:
    pass

class UNEQUAL:
    pass

def check_lists(A, B):
    if A == B:
        return EQUAL
    elif len(A) > len(B) and issublist(B, A):
        return SUPERLIST
    elif len(B) > len(A) and issublist(A, B):
        return SUBLIST
    else:
        return UNEQUAL

def issublist(shorter, longer):
    s = len(shorter)
    l = len(longer)
    for i in range(l-s+1):
        if shorter == longer[i:i+s]:
            return True
    return False
