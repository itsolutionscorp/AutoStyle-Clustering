SUBLIST, SUPERLIST, EQUAL, UNEQUAL = 1, 2, 3, 4

def check_lists(alist, blist):
    '''Given 2 lists returns their relationship'''
    if alist == blist: return EQUAL
    alist, blist  = ''.join(map(str, alist)), ''.join(map(str, blist))

    if blist.find(alist) != -1: return SUBLIST
    if alist.find(blist) != -1: return SUPERLIST
    return UNEQUAL
