def slices(numbers, length):
	num = list(numbers)
	numberList = []
	for item in num:
		numberList.append(int(item))
	count = length
	answer = []
	if len(numbers) >= length and length != 0:
		for number in range(len(numberList)):
			series = numberList[number:count]
			count += 1
			if len(series) == length:
				answer.append(series)
		return answer
	else:
		raise ValueError()
