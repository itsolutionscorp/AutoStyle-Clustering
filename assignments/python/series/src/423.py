def slices(digits, length):
	if length > len(digits) or length <= 0:
		raise ValueError	
	consecutives_list = []
	for n in range(len(digits)-length+1):
		consecutives_list.append(map(int,list(digits[n:n+length])))
	return consecutives_list
