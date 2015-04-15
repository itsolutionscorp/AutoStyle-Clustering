EQUAL = (True,True)
SUBLIST = (True,False)
SUPERLIST = (False,True)
UNEQUAL = (False,False)

def check_lists( l1, l2 ):
    return (incl(l1,l2), incl(l2,l1))

def incl( l1, l2 ):
    for s in range(0, len(l2)-len(l1) + 1):
        if l1 == l2[ s : s+len(l1) ]:
            return True
    return False
