def difference(n):
    if isinstance(n,(int,long)) and n >= 1:
        return square_of_sum(n) - sum_of_squares(n)
    else:
        return 0

def square_of_sum(n):
    # Calculate the square of the sum of the first n natural numbers
    # (n + (n-1) + (n-2) + (n-3) + ... + 1)^2
    return ((n * (n+1)) / 2) ** 2

def sum_of_squares(n):
    # Calculate the sum of the squares of the first n natural numbers
    # n^2 + (n-1)^2 + (n-2)^2 + (n-3)^2 + ... + 1^2
    return (n * (n+1) * ((2*n) + 1))/6
