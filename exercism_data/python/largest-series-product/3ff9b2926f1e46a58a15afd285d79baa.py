def largest_product(number, cuts):
	sliced = slices(number, cuts)
	if number == '' or cuts == 0:
		return 1
	biggest = 0
	for cut in sliced:
		temp = 1
		for i in cut:
			temp *= i
		biggest = temp if temp > biggest else biggest
	return biggest

def slices(num, cuts):
	if cuts > len(num):
		raise ValueError  
	return [[int(i) for i in num[i:i+cuts]] for i in range(len(num)) \
	if len(num[i:i+cuts]) == cuts]
