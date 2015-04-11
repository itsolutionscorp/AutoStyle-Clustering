def difference(numbers):
    return square_of_sum(numbers) - sum_of_squares(numbers)


def square_of_sum(numbers):
    return sum(range(numbers+1))**2


def sum_of_squares(numbers):
    return sum(x**2 for x in range(numbers+1))
