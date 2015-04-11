def on_square(square):
	return 2**(square - 1)  

def total_after(square):
	return sum([on_square(x+1) for x in range(0, square)])
