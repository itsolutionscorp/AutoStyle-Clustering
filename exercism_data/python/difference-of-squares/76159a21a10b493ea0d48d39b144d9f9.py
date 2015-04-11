
def difference(number):
	s1 = square_of_sum(number)
	s2 = sum_of_squares(number)
	if s1 > s2:
		return s1 - s2
	else:
		s2 - s1


def square_of_sum(number):
	total = 0
	for i in range (1, (number + 1)):
		total += i
	total = total**2
	return total


def sum_of_squares(number):
	total = 0
	for i in range(1, (number + 1)):
		total += i**2
	return total

