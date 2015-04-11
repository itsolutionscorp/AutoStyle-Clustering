def difference(a):
    return square_of_sum(a) - sum_of_squares(a)

def square_of_sum(a):
    c = 0
    for b in range(1, a+1):
        c += b
    return c**2

def sum_of_squares(a):
    c = 0
    for b in range (1, a+1):
        c += b**2
    return c
