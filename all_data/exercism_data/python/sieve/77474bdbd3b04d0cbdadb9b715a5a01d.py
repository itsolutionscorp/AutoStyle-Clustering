def sieve(limit):
    test_range = [x for x in range(2, limit + 1)]
    for prime in test_range:
        test_range = rem_multiples(test_range, prime, 2)
    return test_range

def rem_multiples(list, prime, multiplier):
    possible_prime = prime * multiplier
    if possible_prime in list:
        list.remove(possible_prime)
    if not (prime * (multiplier + 1)) > list[-1]:
        return rem_multiples(list, prime, multiplier + 1)
    return list
