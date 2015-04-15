from math import sqrt

def triplets_in_range(min,max):
    triplets = []
    for a in range(min,max+1):
        for b in range(min,max+1):
            c = sqrt(a**2+b**2)
            if c <= max and int(c) == c:
                triplets.append(tuple(sorted([a,b,c])))
    return set(triplets)
    
def primitive_triplets(b):
    if b % 4 != 0:
        raise ValueError("b must be divisible by 4")
    triplets = []
    for m in range(1,b//2 + 1):
        if b/(2*m) != b//(2*m):
            continue
        n = b//(2*m)
        if n > m:
            continue
        if (m - n) % 2 == 0:
            continue
        coprime = True
        for i in range(3,n+1,2):
            if m % i == 0 and n % i == 0:
                coprime = False
        if not coprime:
            continue
        a = m**2-n**2
        c = m**2+n**2
        triplets.append(tuple(sorted([a,b,c])))
    return set(triplets)
    
def is_triplet(triplet):
    t = sorted(triplet)
    return t[0]**2+t[1]**2 == t[2]**2
