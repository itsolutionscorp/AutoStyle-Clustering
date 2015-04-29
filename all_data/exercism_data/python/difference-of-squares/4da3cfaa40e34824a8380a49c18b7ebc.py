def square_of_sum(n):
    return sum(range(n+1))**2

def sum_of_squares(n):
    sq = []
    for x in range(n+1):
        sq.append(x**2)
    return sum(sq)

def difference(n):
    return square_of_sum(n) - sum_of_squares(n)
