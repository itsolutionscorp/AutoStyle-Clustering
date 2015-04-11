def is_triplet(abc):
    [a,b,c] = sorted(abc)
    return a**2 + b**2 == c**2

def triplets_in_range(low,high):
    triplets = set()
    for a in xrange(low,high):
        for b in xrange(a,high):
            c = (a**2 + b**2)**0.5
            if int(c) == c and int(c) <= high:
                triplets.add((a,b,int(c)))
    return triplets

def primitive_triplets(b):
    if b % 4:
        raise ValueError("Input needs to be divisible by 4.")
    pairs = find_factor_pairs(b)
    triplets = set()
    for pair in pairs:
        a = abs(pair[0]**2-pair[1]**2)
        c = pair[0]**2+pair[1]**2
        if is_coprime(sorted([a,b,c])):
            triplets.add(tuple(sorted([a,b,c])))
    return triplets

def find_factor_pairs(n):
    pairs = []
    for i in xrange(1,int((n//2)**(0.5))+1):
        if n/2 % i == 0 and i != (n/2)/i:
            pairs.append((i,(n/2)/i))
    return pairs

def is_coprime(abc):
    for i in xrange(2,abc[0]):
        if all([not j % i for j in abc]):
            return False
    return True
