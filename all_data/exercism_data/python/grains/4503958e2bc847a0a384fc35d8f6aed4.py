on_square = lambda x: 2**(x-1)

total_after = lambda x: sum(on_square(i+1) for i in range(x))
