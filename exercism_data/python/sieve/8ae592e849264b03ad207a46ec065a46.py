import collections

def sieve(upper):
    spread = list(range(2, upper))
    results = []

    while(len(spread) > 0):
        n = spread.pop(0)
        if not any(n % x == 0 for x in results):
            results.append(n)
    return results
