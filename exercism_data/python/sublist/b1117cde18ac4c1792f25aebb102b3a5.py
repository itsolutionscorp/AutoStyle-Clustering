UNEQUAL = 0
SUBLIST = 1
SUPERLIST = 2
EQUAL = 3

def check_lists(list1, list2):
    result = 0

    #CHECK SUBLIST
    i = 0
    while i + len(list1) <= len(list2):
        if list1 == list2[i:i+len(list1)]:
            result += SUBLIST
            break
        i += 1

    #CHECK SUPERLIST
    i = 0
    while i + len(list2) <= len(list1):
        if list2 == list1[i:i+len(list2)]:
            result += SUPERLIST
            break
        i += 1

    return result
