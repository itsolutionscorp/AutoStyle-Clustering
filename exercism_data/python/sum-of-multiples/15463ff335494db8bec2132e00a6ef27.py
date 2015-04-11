def sum_of_multiples(arg1, *args):
	numbers=[]
	div = [3,5]
	for x in args:
		div = x
	for x in range(1, arg1):		
		for y in div:
			if y != 0 and x%y==0 and x not in numbers:
				numbers.append(x)
	return sum(numbers)
