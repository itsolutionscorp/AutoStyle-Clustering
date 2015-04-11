#/usr/bin/env python
def square_of_sum(nnum):
	return ( sum( range(nnum+1) ) )**2 
def sum_of_squares(nnum):
	return sum([x**2 for x in range(nnum+1)])
def difference(nnum):
	return square_of_sum(nnum) - sum_of_squares(nnum)
