EQUAL     = 1
UNEQUAL   = 2
SUBLIST   = 3
SUPERLIST = 4


def is_sublist(l1, l2):
    l1_len = len(l1)
    l2_len = len(l2)
    # if l1 is larger than l2 it can't be a sublist
    if l1_len > l2_len:
        return False
    # loop through each element in l2
    for l2i in range(l2_len):
        count = 0
        # loop through each element in l1
        for l1i in range(l1_len):
            # if list elements don't match exit the loop
            if l2[l2i + count] != l1[l1i]:
                break
            # otherwise increment a counter
            else:
                count += 1
        # if the counter matches the length of l1 then all elements in 1 matched
        if count == l1_len:
            return True


def check_lists(l1, l2):
    if l1 == l2:
        return EQUAL
    else:
        if is_sublist(l1, l2):
            return SUBLIST
        elif is_sublist(l2, l1):
            return SUPERLIST
        else:
            return UNEQUAL
