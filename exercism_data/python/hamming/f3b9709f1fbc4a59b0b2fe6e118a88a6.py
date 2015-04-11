def distance(orig, test):
		
		counter = 0
		for i in range(len(orig)):
			if list(orig)[i] != list(test)[i]: counter += 1
		return counter
