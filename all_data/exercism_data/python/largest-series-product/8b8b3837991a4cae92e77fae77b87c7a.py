def largest_product( N, size ):
    if size == 0 and len(N) == 0:
        return 1
    largest_prod = 0
    for seq in slices( N, size ):
        my_prod = 1
        for d in seq:
            my_prod = my_prod * d
        if my_prod > largest_prod:
            largest_prod = my_prod
    return largest_prod


#  Reused code!
def slices( N, size ):

    if (size == 0 and len(N) > 0) or (len(N) < size):
        raise ValueError('Bad input!')

    return [ [ int(d) for d in N[s:s+size] ]
             for s in range( len(N) - size + 1 ) ]
