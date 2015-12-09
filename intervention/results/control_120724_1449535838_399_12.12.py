def num_common_letters(first, second):
	lst1, lst2 = [], []
	filteredfirst = [lst1.append(letter) for letter in first if letter not in lst1]
	filteredsecond = [lst2.append(letter) for letter in second if letter not in lst2]
	counter = 0
	for letter in filteredsecond:
		if letter in filteredfirst:
			counter += 1
	return counter