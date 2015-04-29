def is_coprime(n1, n2):
    while n1*n2:
        if n1 > n2:
            n1 %= n2
        else:
            n2 %= n1
        return max(n1,n2) == 1

def primitive_triplets(b):
    result = []
    mn = b / 2

# can improve the algorithm
    for n in range(1, mn):
        m = mn / n
        if (m - n) > 0 and (m - n) % 2 != 0 and is_coprime(m, n):
            print m
            print n 
            a = m**2 - n**2
            c = m**2 + n**2
            result.append((a, b, c))

    return result

def triplets_in_range(up, to):
    pass

def is_triplet(triangle):
    pass
