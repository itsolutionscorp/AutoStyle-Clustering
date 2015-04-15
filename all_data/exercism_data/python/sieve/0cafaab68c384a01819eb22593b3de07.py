# I use negative values as the marker for non-prime numbers

def sieve(limit):
    number_range = list(range(2,limit + 1))
    for prime_index in range(len(number_range)):
        if number_range[prime_index] > 0:
            for checkindex in range(prime_index + 1, len(number_range)):
                if number_range[checkindex] > 0 and number_range[checkindex] % number_range[prime_index] == 0:
                    number_range[checkindex] *= -1

    return [ num for num in number_range if num > 0]
