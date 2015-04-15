def slices( N, size ):

    if (size == 0 and len(N) > 0) or (len(N) < size):
        raise ValueError('Bad input!')

    return [ [ int(d) for d in N[s:s+size] ]
             for s in range( len(N) - size + 1 ) ]
