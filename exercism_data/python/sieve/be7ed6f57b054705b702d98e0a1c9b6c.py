def sieve(limit):
    limit = limit + 1  # inclusive of last element
    results = range(2, limit)
    for num in range(2, limit):
        for mult in range(num+num, limit, num):
            if mult in results:
                results.remove(mult)
    return results
