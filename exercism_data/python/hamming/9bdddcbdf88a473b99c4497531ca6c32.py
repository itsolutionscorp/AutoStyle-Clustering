def distance(a,b):
	if len(a) != len(b):
		raise ValueError("Wrong input!")
    
	return sum(first != second for first, second in zip(a, b))
