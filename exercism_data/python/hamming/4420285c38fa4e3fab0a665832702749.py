def distance( s1, s2 ):
    hamming = 0
    for i, c in enumerate( s1 ):
        if c != s2[ i ]:
            hamming += 1
    return hamming
