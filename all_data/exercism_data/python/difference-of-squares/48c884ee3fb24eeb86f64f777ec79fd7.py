def difference(num):
	return square_of_sum(num) - sum_of_squares(num)


def  square_of_sum(num):
	y = sum(range(1,num+1))**2
	return y

def sum_of_squares(num):
	y = sum([x**2 for x in range(1,num+1)])
	return y
      
