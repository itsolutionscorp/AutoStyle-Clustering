def sum_of_multiples(n, numbers=None):
    if numbers is None:
        numbers = [3, 5]

    if 0 in numbers:
        numbers.remove(0)

    total = 0

    for i in range(1, n):
        if any(i % t == 0 for t in numbers):
            total += i

    return total
