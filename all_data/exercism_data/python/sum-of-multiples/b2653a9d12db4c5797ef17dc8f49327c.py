def sum_of_multiples(n, args = [3, 5]):	
	return sum(x for x in range(n) if any(not x%y for y in args if y > 0))
