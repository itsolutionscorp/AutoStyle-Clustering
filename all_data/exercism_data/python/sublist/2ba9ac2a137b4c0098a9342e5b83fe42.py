SUBLIST, SUPERLIST, EQUAL, UNEQUAL = range(0, 4)

def check_lists(l1, l2):
    if l1 == l2:
        return EQUAL
    if l2 == []:
        return SUPERLIST
    if l1 == []:
        return SUBLIST
    if check_list_is_sublist(l1, l2):
        return SUBLIST
    if check_list_is_sublist(l2, l1):
        return SUPERLIST
    return UNEQUAL

def check_list_is_sublist(candidate, parent):
    for i in range(0, len(parent)):
        if parent[i] != candidate[0]:
            continue
        if parent[i:i+len(candidate)] == candidate:
            return True
    return False
