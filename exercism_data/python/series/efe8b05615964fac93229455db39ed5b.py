def slices(digits, length):
#takes in a series of digits 
#and returns all consecutive
#numbers permutations possible

	if length > len(digits) or length <= 0:
		raise ValueError	
		
	consecutives_list = []
	for n in range(len(digits)-length+1):
		#Takes the consecutive digits and converts them to a list of integers,
		#Then adds them to the empty list consecutives_list
		consecutives_list.append(map(int,list(digits[n:n+length])))
	return consecutives_list
