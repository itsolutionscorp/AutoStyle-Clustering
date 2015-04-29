EQUAL = 0
UNEQUAL = -1
SUBLIST = 1
SUPERLIST = 2


def is_in(list1, list2):
    for i in range(0, len(list1)):
        if list2 == list1[i:i + len(list2)]:
            return True
    return False


def check_lists(list1, list2):

    if list1 == list2:
        return EQUAL

    if len(list1) > len(list2) \
            and is_in(list1, list2):
        return SUPERLIST

    if len(list1) < len(list2) \
            and is_in(list2, list1):
        return SUBLIST

    return UNEQUAL
