def gcd(x, y):
    while y:
        x, y = y, x%y
    return x

def primitive_triplets(b):
    sets = []
    
    if b%4:
        raise ValueError
    
    mn = b/2; n = 1
    while n<mn**.5:
        if not mn%n:
            m = mn/n
            if gcd(m, n)==1:
                a = m**2 - n**2
                c = m**2 + n**2
                sets.append(tuple(sorted([a,b,c])))
        n += 1
    
    return set(sets)

def triplets_in_range(start, end):
    triplets_range = []
    
    for i in range(end)[::4]:
        for triplet in primitive_triplets(i):
            for multiple in range(1, end/triplet[-1]+1):
                if triplet[0]*multiple >= start:
                    triplets_range.append(tuple(t*multiple for t in triplet))
    
    return set(triplets_range)

def is_triplet(triplet):
    a,b,c = sorted(triplet)
    if a**2 + b**2 == c**2:
        return True
    return False

def main():
    targ = None
    i = 4
    while not targ:
        for triplet in primitive_triplets(i):
            if not 1000%sum(triplet):
                print tuple(t*1000/sum(triplet) for t in triplet)
            i += 4
