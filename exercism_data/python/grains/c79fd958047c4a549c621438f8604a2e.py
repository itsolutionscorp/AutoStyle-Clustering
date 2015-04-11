def on_square(n):
	return 2**(n-1)

def total_after(n):
	return sum((2**(x) for x in range(0,n)))
