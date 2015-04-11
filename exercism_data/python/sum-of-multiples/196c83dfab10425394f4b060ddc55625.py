def sum_of_multiples(limit, divisors = None):
    if not divisors:
        divisors = [3, 5]

    return sum(i for i in range(1, limit) if divisible(i, divisors))

def divisible(num, divisors):
    return reduce(lambda x, y: x or y, map(lambda z: z > 0 and num % z == 0, divisors))
