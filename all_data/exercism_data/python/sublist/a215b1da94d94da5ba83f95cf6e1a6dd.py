SUBLIST, SUPERLIST, EQUAL, UNEQUAL = range(4)

def check_lists(A, B):
    
    if A == B:
        return EQUAL
    elif A in check_slice(B, len(A)):
        return SUBLIST
    elif B in check_slice(A, len(B)):
        return SUPERLIST
    else:
        return UNEQUAL
        
def check_slice(checkList, length):

    """will return list of slices from checkList (first list above) that are all
     of size=len(second list above). Slices start from the left side of 
     checkList and shift right 1 space, selecting the next group of
     len(second list above) elements. This is repeated until the last element
     in checkList has been included in a slice"""
     
    return [checkList[x:x+length] for x in xrange(len(checkList)-length+1)]
