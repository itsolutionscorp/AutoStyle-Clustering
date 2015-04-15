def nth_prime(p):
    a = [3]

    if p < 3:
        return p + 1
    p -= 2

    i = 0
    n = 3
    while True:
        if a[i] * a[i] < n:
            i = i + 1
        for x in xrange(i + 1):
            if n % a[x] == 0:
                break
        else:
            p -= 1
            if p:
                a.append(n)
            else:
                return n

        n += 2
