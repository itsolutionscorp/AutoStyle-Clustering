__author__ = 'Flavio Miranda'
SUBLIST, SUPERLIST, EQUAL, UNEQUAL = range(0,4)


def check_lists(l1,l2):
    lenL1, lenL2 = len(l1), len(l2)
    indx1 = 0
    if l1 == l2:
        return EQUAL
    if lenL1 >= lenL2:
        while indx1 < (lenL1-lenL2+1):
            if l2 == l1[indx1:indx1+lenL2]:
                return SUPERLIST
            indx1 += 1
    if lenL2 > lenL1:
        while indx1 < (lenL2-lenL1+1):
            if l1 == l2[indx1:indx1+lenL1]:
                return SUBLIST
            indx1 += 1
    return UNEQUAL
