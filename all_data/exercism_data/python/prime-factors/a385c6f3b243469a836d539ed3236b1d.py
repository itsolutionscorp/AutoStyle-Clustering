# @profile
def prime_factors(n):
    factors = []
    for p in range(2, max(10, int(n/2))):
        while n % p == 0:
            factors.append(p)
            n /= p
            if n == 1:
                return factors
    return factors


if __name__ == "__main__":
    # n = 93819012551
    n = 101**9
    print(sum(prime_factors(n)))
