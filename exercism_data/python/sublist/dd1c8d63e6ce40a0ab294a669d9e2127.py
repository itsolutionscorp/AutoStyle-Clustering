__author__ = 'tracyrohlin'

import collections

def EQUAL(x, y):
    return collections.Counter(set(x)) == collections.Counter(set(y))

def UNEQUAL(x, y):
    return collections.Counter(set(x)) != collections.Counter(set(y))

def SUPERLIST(x, y):
    return set(x).issuperset(set(y))

def SUBLIST(x, y):
    return set(x).issubset(set(y))


multiples_of_3 = list(range(3, 200, 3))
multiples_of_15 = list(range(3, 200, 15))

#def check_lists(list1, list2):


print "OF THREE", multiples_of_3
print "of 15", multiples_of_15
