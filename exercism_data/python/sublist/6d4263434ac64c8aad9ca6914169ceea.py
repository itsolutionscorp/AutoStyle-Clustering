'''exer sublist'''

SUBLIST, SUPERLIST, EQUAL, UNEQUAL = 0, 1, 2, 4

def is_sublist(xlst, ylst):
    '''return True if all of x is in consecutive order in y'''
    xlen = len(xlst)
    for ndx in range(len(ylst) - (xlen - 1)):
        if xlst == ylst[ndx:ndx+xlen]:
            return True
    return False

def check_lists(a_lst, b_lst):
    '''test if list A is sub/super/equal/unequal to list B'''

    if a_lst == b_lst:
        return EQUAL

    if is_sublist(a_lst, b_lst):
        return SUBLIST

    if is_sublist(b_lst, a_lst):
        return SUPERLIST

    return UNEQUAL
