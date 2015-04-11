PrimeList = [2]

def fast_generate_primes(lim):
    # the following is from
    # https://github.com/helioha/project-euler-solutions/blob/master/007%20-%2010001st%20prime/extras/
    # sieve_of_eratosthenes.py

    # Python implementation of the sieve of Eratosthenes
    # This is a modified version of the original code available at
    # http://en.wikibooks.org/wiki/Algorithm_Implementation/Mathematics/Prime_number_generation
    global PrimeList
    n = int(lim)
    markedlist = [False] * (n + 1)
    for i in range(2, n + 1):
        if not markedlist[i]:
            if PrimeList[-1] < i:
                PrimeList.append(i)
            for m in range(i ** 2, n + 1, i):
                markedlist[m] = True

def prime(x):
    global PrimeList
    n = int(x)
    if len(PrimeList) >= n:
        return PrimeList[n - 1]
    else:
        fast_generate_primes(PrimeList[-1] * 2)
        return prime(n)

nth_prime = prime
