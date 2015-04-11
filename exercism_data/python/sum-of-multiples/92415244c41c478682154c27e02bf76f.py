def sum_of_multiples(n, a = [3, 5]):
    if a[0] == 0:
        a = a[1:]
    x = [z for z in xrange(n) if any(z % i == 0 for i in a)]
    return sum(x)
