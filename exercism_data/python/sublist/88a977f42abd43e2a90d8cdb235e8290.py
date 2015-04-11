def EQUAL():
    return true
    
def UNEQUAL():
    return true

def SUBLIST():
    return true
    
def SUPERLIST():
    return true
    
    

def check_lists(l1, l2):
    # check if EQUAL
    if l1 == l2:
        return EQUAL
    else:
        # test for sublist
        sublist = True
        for element in l1:
            if element not in l2:
                sublist = False
                break
        # test for superlist
        superlist = True
        for element in l2:
            if element not in l1:
                superlist = False
                break
        # check if SUBLIST
        if sublist == True and superlist == False:
            return SUBLIST
        # check if SUPERLIST
        elif superlist == True and sublist == False:
            return SUPERLIST
        # check if UNEQUAL
        else:
            return UNEQUAL
