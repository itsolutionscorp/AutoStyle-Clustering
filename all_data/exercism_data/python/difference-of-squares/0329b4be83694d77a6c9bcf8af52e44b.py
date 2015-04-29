#difference, square_of_sum, sum_of_squares

def difference(num):
	return square_of_sum(num) - sum_of_squares(num)



def square_of_sum(num):
	return ((1+num)*num/2)**2



def sum_of_squares(num):
	result=0
	for i in range(1,num+1):
		result=result+i**2
	return result
