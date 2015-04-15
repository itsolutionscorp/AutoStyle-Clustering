def sieve(n):
    numbers = []
    primes = []
    for x in range(2, n):
        numbers.append(x)

    count = 0
    while count < len(numbers):
        if numbers[count] != '':
            primes.append(numbers[count])
            # print numbers[count]
            for x in range(count, len(numbers), numbers[count]):
                numbers[x] = ''
        count += 1

    return primes
