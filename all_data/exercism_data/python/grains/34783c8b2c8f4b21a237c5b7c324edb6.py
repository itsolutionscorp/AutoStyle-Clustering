def on_square(i):
	return 2**(i-1)

def total_after(square):
	return sum(2**i for i in range(square))
