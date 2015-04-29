import numpy as np


def sieve(n):
    is_prime = np.ones(n, dtype=np.bool)
    is_prime[:2] = False
    is_prime[4::2] = False

    n_max = int(np.sqrt(n)) + 1
    for i in range(3, n_max, 2):
        is_prime[i * i::i] = False
    return np.nonzero(is_prime)[0].tolist()
