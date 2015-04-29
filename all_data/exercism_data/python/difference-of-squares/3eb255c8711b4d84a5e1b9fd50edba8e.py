sum_of_squares = lambda n: sum(i**2 for i in range(n+1))
square_of_sum = lambda n: ((n*(n+1))//2)**2
difference = lambda n: square_of_sum(n) - sum_of_squares(n)
