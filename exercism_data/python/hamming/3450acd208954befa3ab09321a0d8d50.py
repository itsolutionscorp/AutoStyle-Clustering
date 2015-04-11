def distance(strand1, strand2):
    if len(strand1) == len(strand2): 
        return len([x for x, y in zip(strand1, strand2) if x != y])
    else:
        raise AssertionError
