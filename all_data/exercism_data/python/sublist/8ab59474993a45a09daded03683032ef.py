from itertools import zip_longest
SUBLIST = 0
SUPERLIST = 1
EQUAL = 2
UNEQUAL = 3

def check_lists(l1, l2):
    #the first list is contained within the second list
    if len(l1) > len(l2):
        if check(l1,l2):
            return SUPERLIST
    #the second list is contained within the first list
    if len(l2) > len(l1):
        if check(l2,l1):
            return SUBLIST
    #both lists are contained within each other
    if len(l1) == len(l2):
        return EQUAL if equal(l1, l2) else UNEQUAL
    return UNEQUAL

def check(l1, l2):
    for i in range(len(l1)+1-len(l2)):
        if equal(l1[i:i+len(l2)], l2):
            return True

def equal(l1,l2):
    zipped = zip_longest(l1, l2)
    for tup in zipped:
        if tup[0] != tup[1]:
            return False
    return True
