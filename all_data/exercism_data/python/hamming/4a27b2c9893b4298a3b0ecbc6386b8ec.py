def hamming (first, second):
	if first == second:
		return 0
	else:
		position = 0
		changes = abs(len(first) - len(second))
		for letter in first:
			if position < len(second):
				if letter != second[position]:
					changes = changes + 1
			position = position + 1
		return changes
