def square_of_sum(n):
    return sum(range(1,n+1)) * sum(range(1,n+1))

def sum_of_squares(n):
    outSum = 0
    for value in range(1,n+1):
        outSum += value**2
    return outSum

def difference(n):
    return square_of_sum(n) - sum_of_squares(n)
