UNEQUAL, EQUAL, SUBLIST, SUPERLIST = range(4)

def check_lists(lst1, lst2):
    def is_sublist(lhs, rhs):
        for i in range(len(rhs) - len(lhs) + 1):
            if lhs == rhs[i:i+len(lhs)]:
                return True
        return False
    if lst1 == lst2:
        return EQUAL
    elif len(lst1) < len(lst2) and is_sublist(lst1, lst2):
        return SUBLIST
    elif len(lst1) > len(lst2) and is_sublist(lst2, lst1):
        return SUPERLIST
    else:
        return UNEQUAL
