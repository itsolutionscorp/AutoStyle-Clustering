def square_of_sum(n):
    # the explicit form for the sum of all natural numbers for 1 to n is n*(n+1)/2
    # square that and you get:
    return n*n*(n+1)*(n+1)/4

def sum_of_squares(n):
    # again, there is a neat explicit form for the sum of all squares
    return n*(n+1)*(2*n+1)/6

def difference(n):
    # with a bit of algebra square_of_sum - sum_of_squares simplifies to:
    return n*(n+1)*(n-1)*(3*n+2)/12
