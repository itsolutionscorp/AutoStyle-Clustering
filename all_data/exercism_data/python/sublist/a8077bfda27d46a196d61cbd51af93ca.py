__author__ = 'emiller42'

EQUAL, UNEQUAL, SUBLIST, SUPERLIST = range(4)


def check_lists(first_list, second_list):
    if first_list == second_list:
        return EQUAL
    if first_list in subsets(second_list, len(first_list)):
        return SUBLIST
    if second_list in subsets(first_list, len(second_list)):
        return SUPERLIST
    return UNEQUAL


def subsets(list, length):
    return (list[i:i+length] for i in range(len(list) - length + 1))
