def sieve(number):
    return [x for x in range(2, number + 1) if is_prime(x)]

def is_prime(number):
    if number % 2 == 0 and number != 2:
        return False
    for divisor in range(3, int(number**0.5) + 1, 2):
        if number % divisor == 0:
            return False
    return True
