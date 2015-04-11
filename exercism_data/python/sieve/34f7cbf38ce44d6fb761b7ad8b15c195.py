def is_prime(number):
    return not any(number % num == 0 for num in range(2, number))


def sieve(number):
    return [num for num in range(2, number + 1) if is_prime(num)]
