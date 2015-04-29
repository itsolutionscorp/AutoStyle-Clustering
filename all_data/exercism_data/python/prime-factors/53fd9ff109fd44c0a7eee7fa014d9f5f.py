# Calculate the prime factors for a given number
def prime_factors(number):
    # Start with 2, the first prime number
    prime_number = 2
    result = []

    # Iterate for as long as the number
    while number > 1:

        # If the number is divisible by this prime number, add it to the list
        # (This could happen multiple times)
        # Divide the number by this primer to decrease its value
        if number % prime_number == 0:
            result.append(prime_number)
            number /= prime_number
            
        # The number is not divisible by this prime number, skip it
        else:
            prime_number += 1

    return result
