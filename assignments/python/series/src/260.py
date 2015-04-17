def slices(x, y):
	if len(x) < y or y <= 0:
		raise ValueError("Sequence is longer than the string.")
	else:
		toReturn = []
		for i in range(0, len(x)):
			if len(x) - (i + y) < 0:
				break
			else:
				current = []
				for j in range(i, (i+y)):
					current.append(int(x[j]))
				toReturn.append(current)
		return toReturn
