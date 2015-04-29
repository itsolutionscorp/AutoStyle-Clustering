def difference(num):
	a=sum_of_squares(num)
	b=square_of_sum(num)
	return b-a


def square_of_sum(num):
	total=0
	for i in range(num+1):
		total+=i
	return total**2

def sum_of_squares(num):
	total=0
	for i in range(num+1):
		j=i**2
		total+=j
	return total
