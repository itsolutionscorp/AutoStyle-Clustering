def sieve(limit):
    primes = [2]
    numbers_to_test = range(2,limit+1)
    current_number = 2
    while current_number < limit:
        numbers_to_test = [number for number in numbers_to_test if number % current_number != 0]
        if len(numbers_to_test) == 0:
            break
        current_number = min(numbers_to_test)
        primes.append(current_number)
    return primes
