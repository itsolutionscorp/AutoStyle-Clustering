EQUAL = 3
UNEQUAL = 0
SUBLIST = 1
SUPERLIST = 2

def check_lists(list1, list2):
    set1 = set(list1)
    set2 = set(list2)
    sub = all([s in set2 for s in set1])
    sup = all([s in set1 for s in set2])
    return sub + 2*sup
