def square_of_sum(num):
	j = []

	for i in range(1,num+1):
		j.append(i)

	return sum(j)**2

def sum_of_squares(num):
	j = []

	for i in range(1,num+1):
		j.append(i**2)

	return sum(j)

def difference(num):

	return square_of_sum(num) - sum_of_squares(num)
