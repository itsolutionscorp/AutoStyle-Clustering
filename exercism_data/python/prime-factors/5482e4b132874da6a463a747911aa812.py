def prime_factors(number):
    prime_number = 2
    result = []

    while number > 1:
        if number % prime_number == 0:
            result.append(prime_number)
            number /= prime_number
        else:
            prime_number += 1
    return result
