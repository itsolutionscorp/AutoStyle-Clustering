def sum_of_multiples(n, divisors=[3,5]):

    divs = [x for x in divisors if x != 0]

    total = 0

    for x in range(1, n):
        for div in divs:
            if x % div == 0:
                total += x
                break

    return total
