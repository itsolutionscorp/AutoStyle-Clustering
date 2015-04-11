def triplets_in_range(min, max):
    """Return a list of Pythagorean Triplets whose members
    are in the given range.
    """
    triplets = []
    for a in range(min, max+1):
        for b in range(a+1, max+1):
            for c in range(b+1, max+1):
                if is_triplet((a,b,c)):
                    triplets.append((a,b,c))
    return set(triplets)

def is_triplet(triplet):
    """Test a given 3-element tuple to see if it is a pythagorean
    triplet
    """
    a = triplet[0]**2
    b = triplet[1]**2
    c = triplet[2]**2
    return (a+b==c)or(a+c==b)or(b+c==a)

def primitive_triplets(b):
    """Finds all primitive pythagorean triplets that have a given value
    as one part of the triplet. The given value must be divisble by 4.
    """

    #First, eliminate invalid cases where b isn't divisble by 4
    if b%4 != 0:
        raise ValueError("Cannot find triplets from this value.")

    #Next, find all the factor pairs for the value b/2
    factor_pairs = []
    for i in range(1, int((b/2)**0.5)+1):
        if (b//2)%i == 0:
            factor_pairs.append((i, (b/2)//i))

    #Now find factor pairs (n,m) that fit m-n is odd and gcd(m,n)==1
    coprime_pairs = []
    for pair in factor_pairs:
        if ((pair[1]-pair[0])%2==1) and (gcd(pair[0],pair[1])==1):
            coprime_pairs.append(pair)

    #Finally build the pythagorean triple using b and the coprime pairs
    triplets = []
    for (n, m) in coprime_pairs:
        a = int(m**2-n**2)
        c = int(m**2+n**2)
        triplets.append(tuple(sorted((a,b,c))))

    return set(sorted(triplets))

def gcd(a,b):
    """Returns the greatest common divisor of two given numbers"""
    if b == 0:
        return a
    else:
        return gcd(b, a % b)
