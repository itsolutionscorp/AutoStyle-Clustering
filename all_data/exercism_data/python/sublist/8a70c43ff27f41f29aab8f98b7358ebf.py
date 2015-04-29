def check_lists(l1,l2):
    if l1 == l2:
        return EQUAL
    elif is_sublist(l1,l2):
        return SUBLIST
    elif is_sublist(l2,l1):
        return SUPERLIST
    else:
        return UNEQUAL

def is_sublist(l1,l2):
    '''Check if l1 is sublist of l2'''
    return any([l1 == l2[i:i+len(l1)] for i in xrange(len(l2)-len(l1)+1)])


class EQUAL:
    pass

class UNEQUAL:
    pass

class SUBLIST:
    pass

class SUPERLIST:
    pass
