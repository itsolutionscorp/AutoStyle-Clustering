__author__ = 'tracyrohlin'

from fractions import gcd

def factors(n):
    return set(reduce(list.__add__,
                ([i, n//i] for i in range(1, int(n**0.5) + 1) if n % i == 0)))

def is_triplet(num_tuples):
    num_list = list(num_tuples)
    a, b, c = num_list[0], num_list[1], num_list[2]
    a, b, c = a**2, b**2, c**2
    if a+b == c:
        return True
    elif a+c == b:
        return True
    elif b+c == a:
        return True
    return False

def triplets_in_range(minimum, maximum):
    list_of_triplets = []
    for a in xrange(minimum, maximum+1):
        for b in xrange(minimum+1, maximum+1):
            for c in xrange(minimum+2, maximum+1):
                triplets = [a,b,c]
                if is_triplet(tuple(triplets)):
                    triplets.sort()
                    if triplets not in list_of_triplets:
                        list_of_triplets.append([a, b, c])
    list_of_tuples = [tuple(x) for x in list_of_triplets]
    return set(list_of_tuples)

def primitive_triplets(b):
    if b % 4 == 0:
        ls = []
        mnrange = b//2+1
        for m in range(mnrange):
            for n in range(mnrange):
                if (m-n) % 2 != 0 and m > n:
                    if gcd(m, n) == 1:
                        a, c = (m**2)-(n**2), (m**2)+(n**2)
                        triplet = [a, b, c]
                        triplet.sort()
                        if triplet not in ls:
                            if is_triplet(tuple(triplet)):
                                ls.append(tuple(triplet))
        return set(ls)
    else:
        raise ValueError


print primitive_triplets(84)
