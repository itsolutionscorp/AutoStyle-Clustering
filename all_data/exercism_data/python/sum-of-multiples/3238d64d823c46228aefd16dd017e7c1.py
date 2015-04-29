def sum_of_multiples(number, multiples=[3,5]):
    return sum([x for x in range(number) if multiple(x, multiples)])


def multiple(number, multiples):
    return len([x for x in multiples if x != 0 if number % x == 0]) > 0
