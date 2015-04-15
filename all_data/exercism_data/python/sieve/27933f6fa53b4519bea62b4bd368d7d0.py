from math import sqrt

def sieve(upper):
    record = __initialize_sieve(upper)
    i = 2
    while i <= int(sqrt(upper)):
        if record[i] is True:
            j = i ** 2
            while j <= upper:
                record[j] = False
                j += i
        i += 1

    return [n for n, marked in record.items() if marked is True]

def __initialize_sieve(upper):
    return {n: True for n in range(2, upper)}
