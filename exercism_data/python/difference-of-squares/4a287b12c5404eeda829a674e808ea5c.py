def difference(n):
    return(square_of_sum(n) - sum_of_squares(n))

def square_of_sum(n):
    nsum = 0
    for i in range(1, n + 1):
        nsum += i
    return(nsum**2)

def sum_of_squares(n):
    nsum = 0;
    for i in range(1, n + 1):
        nsum += i**2
    return(nsum)
