#!/usr/bin/python

SUPERLIST = 'SUPERLIST'
SUBLIST = 'SUBLIST'
EQUAL = 'EQUAL'
UNEQUAL = 'UNEQUAL'

def check_lists(list1, list2):
    """
    If list1 in list2, returns SUBLIST
    If list2 in list1, returns SUPERLIST
    If the lists are identical, returns EQUAL
    If the lists are the same length, but different, returns UNEQUAL
    """
    if len(list1) == len(list2):
        if test_equal(list1, list2):
            return EQUAL
        else:
            return UNEQUAL
    if list1 == []:
        return SUBLIST

    if list2 == []:
        return SUPERLIST

    elif test_sublist(list1, list2):
        return SUBLIST
    
    elif test_superlist(list1, list2):
        return SUPERLIST
    
    else:
        return UNEQUAL


def test_sublist(l1, l2):
    return contains(l2,l1)

def test_superlist(l1, l2):
    return contains(l1,l2)

def test_equal(l1, l2):
    if not l1 and not l2:
        return True
    elif l1[0] != l2[0]:
        return False
    elif l1 == l2:
        return True
    else:
        return False

def contains(l1, l2):
    """
    Returns True if l1 contains l2, and False if otherwise
    """
    try:
        startindex = l1.index(l2[0])
        while startindex < len(l1):
            if l1[startindex:startindex+len(l2)] == l2:
                return True
            startindex = l1.index(l2[0],startindex+1)
    except:
        return False
