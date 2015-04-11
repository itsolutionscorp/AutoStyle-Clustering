def distance(s,t):

    if len(s) != len(t):
        return( "Error! Input strings have different lengths!!" )

    hamming_distance = 0
    for ii in range(0, len(s)):
        if s[ii] != t[ii]:
            hamming_distance = hamming_distance + 1

    return hamming_distance
