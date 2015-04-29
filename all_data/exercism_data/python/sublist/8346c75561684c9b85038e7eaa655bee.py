SUBLIST = "List 2 contains list 1."
SUPERLIST = "List 1 contains list 2."
EQUAL = "The lists are equal."
UNEQUAL = "The lists are not equal."

def check_lists(list1, list2):

    list1_in_2 = _is_sublist(list1, list2)
    list2_in_1 = _is_sublist(list2, list1)

    if list1_in_2 and list2_in_1: return EQUAL
    if list1_in_2: return SUBLIST
    if list2_in_1: return SUPERLIST
    return UNEQUAL

def _is_sublist(sublist, superlist):

    if not sublist: return True
    if not superlist: return False

    len_sublist = len(sublist)
    len_superlist = len(superlist)
    if len_sublist > len_superlist:
        return False

    try:
        pos = 0
        while pos+len_sublist <= len_superlist:
            pos = superlist.index(sublist[0], pos)
            if sublist == superlist[pos : pos+len_sublist]:
                return True
            pos += 1
    except ValueError:
        return False
