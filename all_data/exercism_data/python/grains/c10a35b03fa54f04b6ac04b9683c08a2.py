
def on_square(square):
	"""
	Number of grains on square
	Since it doubles every square, it is simply 2^(square-1)
	"""
	return 2**(square-1)
	
def total_after(square):
	"""
	Total number of grains on all squares up to and including square
	Equal to the grains on the next square minus 1
	"""
	return 2**(square)-1
