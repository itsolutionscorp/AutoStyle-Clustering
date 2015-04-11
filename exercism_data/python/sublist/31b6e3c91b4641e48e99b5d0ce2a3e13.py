#!path/python27
# William Morris
# exercism.io
# sublist.py

SUBLIST = 'sublist'
SUPERLIST = 'superlist'
EQUAL = 'equal'
UNEQUAL = 'unequal'

def is_sublist(list1,list2):
    len1 = len(list1)
    is_sub = False
    for i in range(len(list2)-len1+1):
        is_sub = is_sub or list1 == list2[i:i+len1]
    return is_sub


def check_lists(list1,list2):
    set1 = set(list1)
    set2 = set(list2)
    if list1 == list2:
        return EQUAL
    if is_sublist(list1,list2):
        return SUBLIST
    if is_sublist(list2,list1):
        return SUPERLIST
    else:
        return UNEQUAL
