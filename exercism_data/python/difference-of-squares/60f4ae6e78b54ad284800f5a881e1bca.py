square_of_sum = lambda x: sum(range(x+1))**2
sum_of_squares = lambda x: sum(map(lambda x: x**2, range(x+1)))

difference = lambda x: abs(square_of_sum(x)-sum_of_squares(x))
