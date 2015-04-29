SUBLIST = 1
SUPERLIST = 2
EQUAL = 3
UNEQUAL = 4

def check_lists(list_one, list_two):
    if set(list_one) == set(list_two) and len(list_one) == len(list_two):
        return EQUAL
    elif set(list_one).issuperset(set(list_two)):
        return SUPERLIST
    elif set(list_one).issubset(set(list_two)):
        return SUBLIST
    elif set(list_one) != set(list_two):
        return UNEQUAL
