def difference(n):
    return abs(sum_of_squares(n) - square_of_sum(n))

def sum_of_squares(n):
    sum_of_squares = 0
    for i in range(1, n+1):
        sum_of_squares += i**2
    return sum_of_squares

def square_of_sum(n):
    square_of_sum = 0
    for i in range(1, n+1):
        square_of_sum += i
    square_of_sum = square_of_sum**2
    return square_of_sum
