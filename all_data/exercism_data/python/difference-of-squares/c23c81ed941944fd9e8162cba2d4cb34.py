def difference(n):
    sum_of = sum_of_squares(n)
    square_of = square_of_sum(n)
    return square_of - sum_of

def square_of_sum(n):
    sum_to_square = sum(range(1,n+1))
    return sum_to_square ** 2

def sum_of_squares(n):
    squares = map(lambda x: x**2, range(1,n+1))
    return sum(squares)
