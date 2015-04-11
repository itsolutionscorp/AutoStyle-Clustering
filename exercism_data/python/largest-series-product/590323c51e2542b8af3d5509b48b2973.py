
def slices(numbers, size):
	answer = []
	length = len(numbers)
	if length < size:
		raise ValueError()
	else:
		numbers = list(numbers)
		numbers = map((lambda x: int(x)), numbers)
		for i in range((length)):
			if len(numbers[i:i+size]) == size:
				answer.append(numbers[i:i+size])
		return answer

		
def largest_product(numbers, size):
	answer = 0
	if size == 0:
		return 1
	groups = slices(numbers, size)
	for i in groups:
		product = reduce(lambda x, y: x*y, i)
		if product > answer:
			answer = product
	return answer
