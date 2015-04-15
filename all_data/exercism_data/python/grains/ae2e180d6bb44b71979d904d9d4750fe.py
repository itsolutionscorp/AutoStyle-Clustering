def on_square(n):
	return 2**(n-1)

def total_after(n):
	return sum(map(lambda a:2**a,range(n)))
