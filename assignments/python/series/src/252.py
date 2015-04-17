def slices(digits, n):
	if not isinstance(digits, "".__class__) or not isinstance(n, int):
		raise ValueError("digits must be a string and n an integer")
	number_of_digits = len(digits)
	if n == 0 or n > number_of_digits:
		raise ValueError("n must be less or equal than the number of elements in digits")
	digits_as_list = [int(i) for i in digits]
	if n == number_of_digits:
		series = [digits_as_list]
	else:
		series = []
		for i in range(number_of_digits-n+1):
			serie = []
			for j in range(n):
				serie.append(digits_as_list[j+i])
			series.append(serie)
	return series
