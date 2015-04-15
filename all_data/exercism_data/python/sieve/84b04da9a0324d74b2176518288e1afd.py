from typing import List


def sieve(n: int) -> List[int]:
    primes = []  # type: List[int]
    for x in range(2, n + 1):
        factors = 0
        for y in range(1, x):
            if not x % y:
                factors += 1
        if factors == 1:
            primes.append(x)
    return primes
