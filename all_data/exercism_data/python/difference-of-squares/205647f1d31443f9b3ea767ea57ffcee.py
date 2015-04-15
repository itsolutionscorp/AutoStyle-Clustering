def square_of_sum(n):
    sm = 0
    for x in range(1, n+1):
        sm += x
    return sm**2

def sum_of_squares(n):
    sq = 0
    for x in range(1, n+1):
        sq += x**2
    return sq

def difference(n):
    diff = square_of_sum(n) - sum_of_squares(n)
    return diff
