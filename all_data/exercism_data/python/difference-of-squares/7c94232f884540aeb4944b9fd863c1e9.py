def square_of_sum(N):
    return sum(range(1, N+1))**2
    
def sum_of_squares(N):
    return sum(number**2 for number in range(1, N+1))

def difference(N):
    return square_of_sum(N) - sum_of_squares(N)
