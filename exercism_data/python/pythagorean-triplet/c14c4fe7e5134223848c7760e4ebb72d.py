def is_coprime(m, n):
    def gcd(a,b):
        return a if b == 0 else gcd(b, a % b)
    return gcd(m,n) == 1

def primitive_triplets(b):
    if b % 4 != 0:
        raise ValueError

    triplets = set()
    mn = b / 2
    
    for n in range(1, mn):
        m = mn / n
        if (m-n) > 0 and (m-n) % 2 != 0 and is_coprime(m,n):
            a = m*m - n*n
            c = m*m + n*n
            triplet = tuple(sorted([a,b,c])) 
            triplets.add(triplet)
    return triplets


def triplets_in_range(up, to):
    pass

def is_triplet(triangle):
    pass


ans = set([(13, 84, 85), (84, 187, 205), (84, 437, 445), (84, 1763, 1765)])
print primitive_triplets(84)
