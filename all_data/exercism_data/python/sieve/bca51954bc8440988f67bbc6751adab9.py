from typing import List, Tuple


def sieve(n: int) -> List[int]:
    primes = []  # type: List[int]
    for x in range(2, n + 1):
        factors = []  # type: List[Tuple[int, int]]
        for y in range(1, x):
            if not x % y:
                factors.append((x, y))
        if len(factors) == 1:
            primes.append(x)

    return primes
