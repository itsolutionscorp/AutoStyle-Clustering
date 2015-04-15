def coprimes(*numbers):
    """Check if numbers are coprime (have no common divisor)"""
    for f in range(2, min(numbers)+1):
        if not any(map(lambda n: n%f,numbers)):
            return False
    return True


def is_triplet(triplet):
    """verifies a**2 + b**2 = c**2"""
    if len(triplet) != 3:
        return False
    else:
        a,b,c = sorted(triplet)
        return a**2 + b**2 == c**2

def is_primitive_triplet(t):
    """True if t is a primitive Pythagorean triplet, False otherwise"""
    return is_triplet(t) and coprimes(*t)


def primitive_triplets(b):
    """Compute all the primitive triplets involving given argument
    note that the argument must be divisible by 4

    uses the formula :
    a=(m^2-n^2), b=2*m*n and c=(m^2+n^2),
    where m and n are coprime and  m-n>0"""
    if b%4:
        raise ValueError("Argument must be divisible by 4")
    else:
        mnset = {(m,n)
                 for m in range(b//2 + 1)
                 for n in range(m)
                 if 2*m*n == b}
        triplets = { tuple(sorted(((m**2-n**2),b,(m**2+n**2)))) for m,n in mnset }
        return set(t for t in triplets if is_primitive_triplet(t))


def triplets_in_range(mini, maxi):
    """returns a set of all the triplets that can
     be composed in given interval"""
    out = set()
    for a in range(mini, maxi + 1):
        for b in range(a, maxi + 1):
            triplet= a, b, int((a**2 + b**2)**0.5)
            if is_triplet(triplet) and triplet[-1] <= maxi:
                out.add(triplet)
    return out
