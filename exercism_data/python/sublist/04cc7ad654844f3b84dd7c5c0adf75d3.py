'''
http://www.maplesoft.com/support/help/Maple/view.aspx?path=verify/superlist
http://www.maplesoft.com/support/help/Maple/view.aspx?path=verify/sublist
Hope this works
'''
SUBLIST, SUPERLIST, EQUAL, UNEQUAL = 0, 1, 2, 3


def check_lists(l1=[],l2=[]):
    l1 = ''.join(str(i) for i in l1)
    l2 = ''.join(str(i) for i in l2)
    if l1 == l2:
        return EQUAL
    elif l1 in l2:
        return SUBLIST
    elif l2 in l1:
        return SUPERLIST
    else:
        return UNEQUAL
