def square_of_sum(n):
    numbers = range(1, n + 1)
    return sum(numbers) ** 2

def sum_of_squares(n):
    numbers = range(1, n + 1)
    squares = [i ** 2 for i in numbers]
    return sum(squares)

def difference(n):
    return square_of_sum(n) - sum_of_squares(n)
