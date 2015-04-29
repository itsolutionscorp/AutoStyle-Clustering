def square_of_sum(N):
    total = 0
    for i in range(1,N+1):
        total += i
    total = total**2
    return total

def sum_of_squares(N):
    total = 0
    for i in range(1,N+1):
        total += i**2
    return total

def difference(N):
    total = square_of_sum(N) - sum_of_squares(N)
    return total
