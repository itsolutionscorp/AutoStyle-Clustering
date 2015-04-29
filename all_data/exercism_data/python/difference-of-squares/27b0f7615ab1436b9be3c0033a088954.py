#
# captainnurple's code for the Python Difference of Squares exercise.
#
# This is a straightforward exercise. The code should be self-explanatory
# We're either summing squares or squaring the sum.
# Each function takes a single argument N, signifying the first N natural numbers
# that you want to operate on.

def square_of_sum(N):
    sum = 0
    for i in range(1, N+1):
        sum += i
    return sum**2

def sum_of_squares(N):
    sum = 0
    for i in range(1, N+1):
        sum += i**2
    return sum

def difference(N):
    return square_of_sum(N) - sum_of_squares(N)
