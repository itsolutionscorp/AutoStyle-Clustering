'''
	This function returns the number of grains on a given square
	For example: the 1st square has 1 grain, but it doubles from then on
'''
def on_square(square_num):
	if square_num == 1:
		return 1
	return on_square(square_num-1)*2

'''
	This function returns the total number of grains 
	on the squares 1 up to the given square
'''
def total_after(square):
	return (sum(on_square(idx) for idx in xrange(1, square+1)))
