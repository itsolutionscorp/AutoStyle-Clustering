# Calculate the difference between the square of the sum of the first n natural numbers
# And the sum of the squares of the first n natural numbers
def difference(n):

    # Validate the input
    if n is not None and isinstance(n,(int,long)) and n >= 1:
        return square_of_sum(n) - sum_of_squares(n)
    else:
        # Invalid input
        return None

# Calculate the square of the sum of the first n natural numbers
# (n + (n-1) + (n-2) + (n-3) + ... + 1)^2
def square_of_sum(n):
    return ((n * (n+1)) / 2) ** 2

# Calculate the sum of the squares of the first n natural numbers
# n^2 + (n-1)^2 + (n-2)^2 + (n-3)^2 + ... + 1^2
def sum_of_squares(n):
    return (n * (n+1) * ((2*n) + 1))/6
