def square_of_sum(N):
    s = sum(range(N+1))
    return s**2

def sum_of_squares(N):
    return sum([n**2 for n in range(N+1)])

def difference(N):
    return square_of_sum(N) - sum_of_squares(N)