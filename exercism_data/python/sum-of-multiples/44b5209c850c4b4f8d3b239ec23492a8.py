def sum_of_multiples(n, f=[3, 5]):
    return sum(filter(
        lambda x: any(map(
            lambda y: y > 0 and x % y == 0,
            f)),
        range(1, n)))
