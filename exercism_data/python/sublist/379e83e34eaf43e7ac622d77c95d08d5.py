
SUBLIST, SUPERLIST, EQUAL, UNEQUAL = (1, 2, 3, 4)



def check_lists(list1, list2):
    if list1 == list2:
        return EQUAL
    
    set1, set2 = set(list1), set(list2)
    if set1 <= set2:
        return SUBLIST
    elif set2 <= set1:
        return SUPERLIST
    else:
        return UNEQUAL
    
