import math
import time


def timeit(f):

    def timed(*args, **kw):

        ts = time.time()
        result = f(*args, **kw)
        te = time.time()

        print('%s took: %2.4f sec' % (f.__name__, te-ts))
        return result

    return timed


@timeit
def sieve_orig(n):
    sieve_range = range(n)
    sieve_result = [1] * n
    sieve_result[0] = sieve_result[1] = 0

    for x in range(2, n):
        if sieve_result[x]:
            y = x * x
            while y < n:
                sieve_result[y] = 0
                y += x
    return [x[0] for x in zip(sieve_range, sieve_result) if x[1] == 1]


@timeit
def sieve(n):
    sieve_range = range(n)
    sieve_result = [1] * n
    sieve_result[0] = sieve_result[1] = 0

    for x in range(2, int(math.sqrt(n))+1):
        if sieve_result[x]:
            for y in range(x*x, n, x):
                sieve_result[y] = 0
    return [x[0] for x in zip(sieve_range, sieve_result) if x[1] == 1]

if __name__ == "__main__":
    sieve_orig(60085)
    sieve_orig(600851)
    sieve_orig(6008514)

    sieve(60085)
    sieve(600851)
    sieve(6008514)
    sieve(10**8)  # This takes approx 29sec

    print(sieve(500) == sieve_orig(500))

    # print(sieve(10))
