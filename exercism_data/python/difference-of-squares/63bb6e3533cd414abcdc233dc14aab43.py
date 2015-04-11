def sum_of_squares(num):
	total=0
	for number in range(num+1):
		total+=number**2
	return total
	
def square_of_sum(num):
	return (sum(range(num+1))**2)

def difference(num):
	return (square_of_sum(num) - sum_of_squares(num))
