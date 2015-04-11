def sieve(num):
    numbers = range(2, num + 1)

    prime_index = 0
    while prime_index < len(numbers):
        prime = numbers[prime_index]

        # remove the composites that are made up by this prime
        composite_index = prime_index + 1
        while composite_index < len(numbers):
            if numbers[composite_index] % prime == 0:
                del numbers[composite_index]
            else:
                composite_index += 1

        prime_index += 1

    return numbers
