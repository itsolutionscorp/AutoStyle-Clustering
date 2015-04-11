def sieve(n):

    test = range(2,n+1)

    for i in test:
        for mult in [i*j for j in range(2,n/i+1)]:
            if mult in test:
                test.remove(mult)

    return test
