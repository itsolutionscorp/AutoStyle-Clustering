def distance(x, y):
	if len(x) == len(y):
		distance = 0
		list_x = list(x)
		list_y = list(y)
		for i in range(len(x)):
			if list_x[i] != list_y[i]:
				distance = distance + 1
		return distance
	else:
		return 
				
