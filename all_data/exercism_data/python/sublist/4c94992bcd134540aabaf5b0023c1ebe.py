SUBLIST = 'sub-list'
SUPERLIST = 'super-list'
EQUAL = 'equal'
UNEQUAL = 'unequal'

def check_lists(list_1, list_2):
    len1 = len(list_1)
    len2 = len(list_2)
    if len1 == len2: #potential equal / unequal.
        if is_sublist(list_2, list_1):
            return EQUAL
        return UNEQUAL
    if len1 < len2: #potential sub-list
        if is_sublist(list_2, list_1):
            return SUBLIST
        return UNEQUAL
    if len1 > len2:  # potential super-list
        if is_sublist(list_1, list_2):
            return SUPERLIST
        return UNEQUAL

        
def is_sublist(larger_list, list_to_check):
    if list_to_check == []:
        return True
    elif list_to_check[0] in larger_list:
        for val in (index for index, x in enumerate(larger_list) if x == list_to_check[0]):
            start = val
            end = start + len(list_to_check)
            if list_to_check == larger_list[start:end]:
                return True
    return False
