UNEQUAL = -1
EQUAL = 0
SUBLIST = 1
SUPERLIST = 2

def check_lists(list1, list2):
    len1 = len(list1)
    len2 = len(list2)

    if len1 == len2:
        return EQUAL if all(item1 == item2 for item1,item2 in zip(list1,list2)) else UNEQUAL
    elif len1 < len2:
        return SUBLIST if is_sublist(list2,list1) else UNEQUAL
    else:
        return SUPERLIST if is_sublist(list1,list2) else UNEQUAL

def is_sublist(lst,sublst):
    n = len(sublst)
    m = len(lst)
    return any(sublst == lst[i:i+n] for i in xrange(m - n + 1))
