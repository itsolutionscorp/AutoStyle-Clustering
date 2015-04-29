# Explicitly calculated the difference formula, given the well
# known sums forumla and sum of squares formula


def difference(N):
    return ((N**3 - N) * (3*N + 2))/12

def square_of_sum(N):
    return ((N * (N+1)) / 2)**2

def sum_of_squares(N):
    return ((N**2 + N) * (2*N + 1))/6
