def square_of_sum(n):
    sum = 0
    for k in range(0,n+1):
        sum += k
    return sum**2

def sum_of_squares(n):
    sum = 0
    for k in range(0,n+1):
        sum += k**2
    return sum

def difference(n):
    return square_of_sum(n) - sum_of_squares(n)
