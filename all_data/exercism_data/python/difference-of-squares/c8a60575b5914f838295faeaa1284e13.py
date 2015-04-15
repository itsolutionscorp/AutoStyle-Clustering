def difference(n):
    return square_of_sum(n) - sum_of_squares(n)

def square_of_sum(n):
    acc = 0
    for i in range(n+1):
        acc += i
    return acc**2

def sum_of_squares(n):
    acc = 0
    for i in range(n+1):
        acc += i**2
    return acc
