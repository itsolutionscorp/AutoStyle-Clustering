#!/usr/bin/python
SUBLIST = 1
SUPERLIST = 2
EQUAL = 4
UNEQUAL = 8

def sublist(list1, list2):
    index = -1
    more = True
    while more:
        more = list2[index+1:].count(list1[0]) > 0
        if not more:
            return False
        index = list2.index(list1[0], index+1)
        if list2[index:index+len(list1)] == list1:
            return True

def check_lists(list1, list2):
    if list1 == list2:
        return EQUAL
    if list1 == []:
        return SUBLIST
    if list2 == []:
        return SUPERLIST
    if len(list1) > len(list2):
        if sublist(list2, list1):
            return SUPERLIST
    else:
        if sublist(list1, list2):
            return SUBLIST
    return UNEQUAL
