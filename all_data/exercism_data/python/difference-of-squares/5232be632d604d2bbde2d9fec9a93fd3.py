def square_of_sum(N):
    return sum([k for k in range(N+1)])**2

def sum_of_squares(N):
    return sum([k**2 for k in range(N+1)])

def difference(N):
    return square_of_sum(N) - sum_of_squares(N)
