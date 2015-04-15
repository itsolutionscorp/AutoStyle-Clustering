def sieve(top):
    xs = []
    for n in range(2, top):
        if not any(n % x == 0 for x in xs):
            xs.append(n)
    return xs
