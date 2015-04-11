def sieve(N):
    candidates = range(2, N+1)
    confirmed = []

    while candidates:
        prime = candidates[0]
        confirmed.append(prime)
        candidates = [i for i in candidates if i % prime]

    return confirmed
