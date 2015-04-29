SUBLIST = 'sublist'
SUPERLIST = 'superlist'
EQUAL = 'equal'
UNEQUAL = 'unequal'

def check_lists(list0, list1):
    if list0 == list1:
        return EQUAL

    if '-'.join(map(str, list0)) in '-'.join(map(str, list1)):
        return SUBLIST

    if '-'.join(map(str, list1)) in '-'.join(map(str, list0)):
        return SUPERLIST

    return UNEQUAL
