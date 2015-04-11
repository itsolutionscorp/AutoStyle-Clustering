global EQUAL
global UNEQUAL
global SUBLIST
global SUPERLIST
EQUAL = "equal"
UNEQUAL = "unequal"
SUBLIST = "sublist"
SUPERLIST = "superlist"

def check_lists(List1,List2):
    #List1 = set(List1)
    #List2 = set(List2)
    String1 = "".join(list(map(str,List1)))
    String2 = "".join(list(map(str,List2)))
    #if sorted(List1) == sorted(List2):
    if String1 == String2:
        return "equal"
    #elif all(e in List2 for e in List1):
    if String1 in String2:
        return "sublist"
    #elif all(e in List1 for e in List2):
    if String2 in String1:
        return "superlist"
    else:
        return "unequal"
