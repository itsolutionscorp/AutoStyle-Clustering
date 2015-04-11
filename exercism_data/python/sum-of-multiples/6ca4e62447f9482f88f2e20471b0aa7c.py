def sum_of_multiples(n, multiples=[3, 5]):
    if multiples[0] == 0:
        multiples.remove(0)

    total = []

    for x in range(1, n):
        for m in multiples:
            if x % m == 0:
                total.append(x)
                break

    return sum(total)
