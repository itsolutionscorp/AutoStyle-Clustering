#differences_of_squares.py
#It's all greek to me!

#check out these beautiful identities and their proofs
#http://www.fredonia.edu/faculty/math/JonathanCox/math/SumOfSquares/SumOfSquares.html


def sum_of_squares(limit):
    return (limit*(limit+1)*(2*limit+1))/6


def square_of_sum(limit):
    return ((limit*(limit+1))/2)**2


def difference(limit):
    return square_of_sum(limit)-sum_of_squares(limit)
