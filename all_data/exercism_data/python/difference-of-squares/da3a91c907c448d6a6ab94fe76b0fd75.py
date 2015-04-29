def sum_of_squares(N):
    return sum(map( lambda x: x**2 , range(1, N+1)))

def square_of_sum(N):
    return sum(range(1, N+1))**2

def difference(N):
    return square_of_sum(N) - sum_of_squares(N)
