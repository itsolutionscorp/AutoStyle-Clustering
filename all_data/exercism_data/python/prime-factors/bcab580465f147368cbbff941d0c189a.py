# @profile
def prime_factors(n):
    factors = []
    for p in range(2, max(10, int(n/2))):
        if n == 1:
            return factors
        while n % p == 0:
            factors.append(p)
            n /= p
    return factors


if __name__ == "__main__":
    # n = 93819012551
    n = 10**10
    # print(prime_factors_orig(n) == prime_factors(n) == prime_factors_old(n))
    prime_factors(n)
