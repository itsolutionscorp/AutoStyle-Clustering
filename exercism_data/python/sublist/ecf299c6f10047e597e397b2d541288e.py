SUBLIST = 'SUBLIST'
SUPERLIST = 'SUPERLIST'
EQUAL = 'EQUAL'
UNEQUAL = 'UNEQUAL'

def check_lists(list_one, list_two):
    if list_one == list_two:
        return EQUAL
    if len(list_one) > len(list_two):
        result = big_little(list_one, list_two)
        if result == True: return SUPERLIST
        return UNEQUAL
    else:
        result = big_little(list_two, list_one)
        if result == True: return SUBLIST
        return UNEQUAL

def big_little(list_one, list_two):
    for num in range(len(list_one) - len(list_two) + 1):
        if list_one[num:len(list_two) + num] == list_two:
            return True
    return False
