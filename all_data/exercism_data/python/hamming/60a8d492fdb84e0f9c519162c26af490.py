def distance(s,t):

    if len(s) != len(t):
        return( "Error! Input strings have different lengths!!" )

    hamming_distance = 0
    for st in zip(s,t):
        if st[0] != st[1]:
            hamming_distance = hamming_distance + 1

    return hamming_distance
