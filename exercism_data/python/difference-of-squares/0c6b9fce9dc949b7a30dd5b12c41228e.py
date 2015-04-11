square_of_sum   = lambda n: ((n**2+n)/2)**2
sum_of_squares  = lambda n: sum((i**2 for i in range(n+1)))
difference      = lambda n: square_of_sum(n) - sum_of_squares(n)
