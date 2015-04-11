__author__ = 'agupt15'


def sum_of_multiples(number, divisors=[3, 5]):
    return sum([x for divisor in divisors for x in range(number) if divisor != 0 and x % divisor == 0 and x > 0])
