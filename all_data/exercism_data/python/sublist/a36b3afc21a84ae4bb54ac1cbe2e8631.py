EQUAL = 1
SUBLIST = 2
SUPERLIST = 3
UNEQUAL = 4

def check_lists( l1, l2 ):
    return {(True,True):EQUAL,
            (True,False):SUBLIST,
            (False,True):SUPERLIST,
            (False,False):UNEQUAL}[ (incl(l1,l2), incl(l2,l1)) ]

def incl( l1, l2 ):
    if len(l2) < len(l1):
        return False
    l2copy = [ e for e in l2 ]
    for e in l1:
        try:
            l2.remove( e )
        except( ValueError ):
            return False
    return True


