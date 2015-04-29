from collections import Counter

def check_lists(l1,l2):

    l1counter, l2counter = Counter(l1), Counter(l2)
    l2counter.subtract(l1counter)

    if all(count == 0 for count in l2counter.itervalues()):
        return EQUAL
    elif all(count >= 0 for count in l2counter.itervalues()):
        return SUBLIST
    elif all(count <= 0 for count in l2counter.itervalues()):
        return SUPERLIST
    else:
        return UNEQUAL


class EQUAL:
    pass

class UNEQUAL:
    pass

class SUBLIST:
    pass

class SUPERLIST:
    pass
