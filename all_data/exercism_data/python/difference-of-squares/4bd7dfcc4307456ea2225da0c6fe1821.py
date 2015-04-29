def difference(N):
    return square_of_sum(N) - sum_of_squares(N)

def square_of_sum(N):
    return sum(range(N+1))**2

def sum_of_squares(N):
    return sum(x**2 for x in range(N+1))
