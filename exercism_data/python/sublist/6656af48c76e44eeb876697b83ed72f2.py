
SUBLIST = 'Sublist'
SUPERLIST = 'Superlist'
EQUAL = 'Equal'
UNEQUAL = 'Unequal'

def check_lists(a,b):
    
    def identify_sublist(list1, list2):
        length1 = len(list1)
        length2 = len(list2)
        if not length1 <= length2:
            return False
        else:
            for i in range(1+(length2 - length1)):
                for x,y in zip(list1,list2[i:i+length1]):
                    if not x == y:
                        break
                else:
                    return True
            return False

    a_in_b = identify_sublist(a,b)
    b_in_a = identify_sublist(b,a)

    if b_in_a and a_in_b:
        return "Equal"
    elif b_in_a and not a_in_b:
        return "Superlist"
    elif a_in_b and not b_in_a:
        return "Sublist"
    else:
        return "Unequal"
