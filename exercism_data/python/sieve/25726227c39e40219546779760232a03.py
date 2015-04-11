
def sieve(limit):
    primes = [2]
    marked = set()

    for next in range(3, limit, 2):
        if next in marked:
            continue
        
        primes.append(next)

        marked.update(range(next*next, limit, next))

    return primes
