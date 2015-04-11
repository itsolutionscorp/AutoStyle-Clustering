EQUAL = 1
UNEQUAL = 2
SUBLIST = 3
SUPERLIST = 4

def check_lists(list1, list2):
    """Determines the relationship between two given lists"""
    one_in_two = check_sublist(list1, list2)
    two_in_one = check_sublist(list2, list1)

    if one_in_two and two_in_one:
        return EQUAL
    elif one_in_two and not two_in_one:
        return SUBLIST
    elif not one_in_two and two_in_one:
        return SUPERLIST
    else:
        return UNEQUAL

def check_sublist(list_one, list_two):
    """Checks to see if the first given list is a sublist of the second"""
    slice_length = len(list_one)

    if slice_length == 0:
        return True
    elif slice_length > len(list_two):
        return False
    else:
        slices_of_two = [list_two[i:i+slice_length]
                         for i
                         in range(len(list_two)-slice_length+1)]
        return list_one in slices_of_two
