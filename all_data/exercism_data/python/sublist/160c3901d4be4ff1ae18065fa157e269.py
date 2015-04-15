def check_lists(a, b):

    if a == b: return EQUAL
    elif len(a) < len(b):
        for i in range(len(b)):
            if a == b[i:len(a)+i]: return SUBLIST
    elif len(b) < len(a):
        for i in range(len(a)):
            if b == a[i:len(b)+i]: return SUPERLIST
    return UNEQUAL

def SUBLIST(): return 'SUBLIST'

def SUPERLIST(): return 'SUPERLIST'

def EQUAL(): return 'EQUAL'

def UNEQUAL(): return 'UNEQUAL'
