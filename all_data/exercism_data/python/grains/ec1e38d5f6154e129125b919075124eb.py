#/usr/bin/env python
def on_square(square):
	return 2**(square-1)
def total_after(square):
	return sum([2**(x) for x in range(square)])
