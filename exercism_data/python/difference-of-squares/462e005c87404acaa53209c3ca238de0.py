def square(x):
    return x**2

def sum_of_squares(n):
    total = 0
    for i in range(1, n+1):
        total = total + square(i)
    return total

def square_of_sum(n):
    total = 0
    for i in range(1, n+1):
        total = total + i 
    return square(total)

def difference(n):
    return (square_of_sum(n) - sum_of_squares(n))
