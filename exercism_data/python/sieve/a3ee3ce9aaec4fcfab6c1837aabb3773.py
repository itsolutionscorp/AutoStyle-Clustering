def sieve_1(limit):
    """
        First attempt
    """
    numbers = {i: True for i in range(2, limit)}
    curr = 2
    while True:
        if curr >= limit:
            break

        if not numbers[curr]:
            curr += 1
            continue

        for i in range(curr * curr, limit, curr):
            numbers[i] = False

        curr += 1
    return (x for x in numbers if numbers[x])


def sieve_2(limit):
    """
        Second attempt
    """
    ret_list = []
    numbers = {i: True for i in range(2, limit)}
    for n in range(2, int(limit ** (1 / 2))):
        if not numbers[n]:
            continue
        for i in range(n * n, limit, n):
            numbers[i] = False
        ret_list.append(n)
    return ret_list


def sieve_3(limit):
    """
        Adapted from:
        http://stackoverflow.com/a/3941967
    """
    numbers = [True] * limit
    numbers[0] = False
    numbers[1] = False
    for (i, val) in enumerate(numbers):
        if val:
            for n in range(i * i, limit, i):
                numbers[n] = False
    return (x for x in numbers if numbers[x])


# So the unittest finds what it is looking for
sieve = sieve_2


if __name__ == '__main__':
    import timeit
    print(timeit.timeit("sieve_1(20)", setup="from __main__ import sieve_1"))
    print(timeit.timeit("sieve_2(20)", setup="from __main__ import sieve_2"))
    print(timeit.timeit("sieve_3(20)", setup="from __main__ import sieve_3"))
