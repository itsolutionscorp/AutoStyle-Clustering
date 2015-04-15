""""
Write a function to determine if a list is a sublists of another list.

Write a function that given two lists determines if the first list is
contained within the second list, if the second list is contained within
the first list, if both lists are contained within each other or if none
of these are true.

"""

EQUAL = True
UNEQUAL = True
SUBLIST = True
SUPERLIST = True

def check_lists(l1, l2):
    
    l1,l2 = set(l1),set(l2)
    #Subset
    if l1 < l2: 
        return SUBLIST  
    #Superset    
    if l1 > l2: 
        return SUPERLIST  
    if l1 == l2: 
        return EQUAL
    else:   
        return UNEQUAL

if __name__ == "__main__":
    l1 = [1, 2, 5]
    l2 = [0, 1, 2, 3, 1, 2, 5, 6]
    print check_lists(l1,l2)
    l1 = [0, 1, 2, 3, 4, 5]
    l2 = [3, 4, 5]
    print check_lists(l1,l2)
