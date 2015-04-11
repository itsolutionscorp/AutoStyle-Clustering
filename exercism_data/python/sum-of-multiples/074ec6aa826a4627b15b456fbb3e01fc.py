import array


def sum_of_multiples(n: int, factors=(3, 5), dtype="i") -> int:
    sv = array.array(dtype, [0] * n)
    multiples = []

    for factor in factors:
        for candidate in range(n):
            if sv[candidate] == 0:
                try:
                    if candidate % factor == 0:
                        sv[candidate] = 1
                        multiples.append(candidate)
                except ZeroDivisionError:
                    continue

    return sum(multiples)
