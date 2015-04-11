#return difference of square_of_sum(upperBound) and sum_of_squares(upperBound)

def difference(upperBound):
	return  square_of_sum(upperBound) - sum_of_squares(upperBound)
	
#sum all in range and then square result	

def square_of_sum(upperBound):
	return sum([x for x in range(1, upperBound+1)]) ** 2

#square all in range and then sum results	

def sum_of_squares(upperBound):
	return sum([x ** 2 for x in range(1, upperBound+1)])
