def difference(what):
	return square_of_sum(what) - sum_of_squares(what)

def square_of_sum(what):
	count = 0
	for i in range(what+1):
            count += i
	return count ** 2

def sum_of_squares(what):
	sumof =0
	for i in range(what+1):
		sumof += i **2
	return sumof
