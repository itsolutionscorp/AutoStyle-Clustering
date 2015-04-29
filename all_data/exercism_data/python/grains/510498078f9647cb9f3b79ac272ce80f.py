_GRAINS = [2**x for x in xrange(64)]

def on_square(square_num):
	return _GRAINS[square_num-1]
def total_after(square_num):
	return sum(_GRAINS[:square_num])
