def square_of_sum(number):
    sum = 0
    for i in range(1, number + 1):
        sum += i
    return sum**2


def sum_of_squares(number):
    sum = 0
    for i in range(1, number + 1):
        sum += (i**2)
    return sum


def difference(n):
    return abs(square_of_sum(n) - sum_of_squares(n))
