SUBLIST = 'sbl'
SUPERLIST = 'sprl'
EQUAL = 'eq'
UNEQUAL = 'un'

def check_lists(list1,list2):

    if len(list1)>len(list2):
        val = SUPERLIST
        list1,list2 = list2,list1
    elif len(list1)<len(list2):
        val = SUBLIST
    elif list1==list2:
        return EQUAL
    for i in range(len(list2)-len(list1)+1):
        if list1==list2[i:len(list1)+i]:
            return val
    return UNEQUAL
