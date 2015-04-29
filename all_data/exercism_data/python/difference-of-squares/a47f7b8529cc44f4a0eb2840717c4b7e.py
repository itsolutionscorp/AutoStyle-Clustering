

def sum_of_squares(N):
    return sum(i**2 for i in range(N + 1))


def square_of_sum(N):
    return sum(range(N + 1))**2


def difference(N):
    return square_of_sum(N) - sum_of_squares(N)
