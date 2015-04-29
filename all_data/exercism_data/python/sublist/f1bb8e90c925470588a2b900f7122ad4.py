EQUAL = 0
SUBLIST = 1
SUPERLIST = 2
UNEQUAL = 3


def check_lists(searched, reference):
    if searched == reference:
        return EQUAL

    if list_in_list(searched, reference):
        return SUBLIST
    elif list_in_list(reference, searched):
        return SUPERLIST

    return UNEQUAL


def list_in_list(small, large):
    if len(small) >= len(large):
        return False
    for index in xrange(len(large) - len(small) + 1):
        if small == large[index:index + len(small)]:
            return True
    return False
