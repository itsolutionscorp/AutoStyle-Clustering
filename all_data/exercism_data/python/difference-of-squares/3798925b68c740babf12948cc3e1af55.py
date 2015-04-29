def square_of_sum(num):
    total = 0
    for i in range(num + 1):
        total += i
    return (total ** 2)


def sum_of_squares(num):
    total = 0
    for i in range(num + 1):
        total += i ** 2
    return total


def difference(num):
    result = abs(square_of_sum(num) - sum_of_squares(num))
    return result
