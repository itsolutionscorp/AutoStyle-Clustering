EQUAL = 1
UNEQUAL = 2
SUBLIST = 4
SUPERLIST = 8

def _sub_list(big, bigN, small, smallN, type):
    """Check each smallN sized piece of big to see if small in big"""
    for x in range(bigN-smallN+1):
        if small == big[x:x+smallN]:
            return type
    return UNEQUAL

def check_lists(l1, l2):
    if l1 == l2:
        return EQUAL
    
    if len(l1) > len(l2):
        type = SUPERLIST
        big,small = l1, l2
    else:
        type = SUBLIST
        big,small = l2, l1
        
    bigN, smallN = map(len,(big,small))

    return _sub_list(big, bigN, small, smallN, type)
