def difference(N):
    return square_of_sum(N)-sum_of_squares(N)


def square_of_sum(N):
    return sum(range(1, N+1))**2


def sum_of_squares(N):
    return sum((num**2 for num in range(1, N+1)))
