from math import sqrt, floor

def is_triplet( triple ):
    a = triple[0]
    b = triple[1]
    c = triple[2]
    return a**2 + b**2 == c**2 or b**2 + c**2 == a**2 or c**2 + a**2 == b**2

def triplets_in_range( n, N ):
    all_triplets = []
    for a in range( n, N+1 ):
        for b in range( a, N+1 ):
            for c in range( b, N+1 ):
                if is_triplet( (a, b, c) ):
                    all_triplets.append( (a,b,c) )
    return set( all_triplets )

def primitive_triplets( b ):
    all_triplets = []
    if not b%4 == 0:
        raise ValueError
    for n in range( 1, floor(sqrt(b/2))+1 ):
        m = b // (2*n)
        if 2*m*n == b and are_coprime( m, n ):
            all_triplets.append(
                tuple( sorted([m**2-n**2, 2*m*n, m**2+n**2]) ) )
    return set( all_triplets )

## Euclidean algorithm
def are_coprime( m_in, n_in ):
    m = max(m_in,n_in)
    n = min(m_in,n_in)
    while n > 0:
        newN = m % n
        m = n
        n = newN
    return m == 1
