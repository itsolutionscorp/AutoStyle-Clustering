__author__ = 'tracyrohlin'

import collections

SUBLIST = 1
SUPERLIST = 2
EQUAL = 4
UNEQUAL = 8

def sublist(masterlist, sublist):
    for i in range(len(masterlist)-len(sublist)+1):
        if sublist == masterlist[i:i+len(sublist)]:
            return True #return position (i) if you wish
    return False

def check_lists(list1, list2):
    if list1 == list2:
        return EQUAL
    elif list1 == []:
        return SUBLIST
    elif list2 == []:
        return SUPERLIST
    elif len(list1) > len(list2):
        if sublist(list1, list2):
            return SUPERLIST
    elif len(list1) < len(list2):
        if sublist(list2, list1):
            return SUBLIST
    return UNEQUAL
