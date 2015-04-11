def sieve(value):
    all_nums = set(xrange(2, value + 1))
    all_non_primes = set()

    for number in all_nums:
        for next_number in all_nums:
            if next_number > number and next_number % number == 0:
                all_non_primes.add(next_number)

    all_nums -= all_non_primes
    return list(all_nums)
