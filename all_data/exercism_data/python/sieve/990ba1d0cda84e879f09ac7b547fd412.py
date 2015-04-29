def sieve(N):
    is_prime = [True] * (N+1)
    is_prime[0:2] = [False, False]
    for i in range(0, int(N**0.5+1)):
        if is_prime[i]:
            for j in range(i*i, N+1, i):
                is_prime[j] = False
    return [i for i in range(0, N+1) if is_prime[i]]
