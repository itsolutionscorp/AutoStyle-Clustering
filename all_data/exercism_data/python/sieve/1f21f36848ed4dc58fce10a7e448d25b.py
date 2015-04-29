from math import floor, sqrt

sieve_one = lambda n, r: filter(lambda x: x%n or x==n, r)

def sieve(x):
    end = int(floor(sqrt(x)))+1
    out = range(2, x+1)

    i = 0
    while out[i] < end:
        out = sieve_one(out[i], out)
        i += 1

    return out
