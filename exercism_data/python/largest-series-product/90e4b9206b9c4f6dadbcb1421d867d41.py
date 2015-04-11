from string import *

def slices(input_string,sub_length):
	l = len(input_string)
	
	if (sub_length < 1):
		raise ValueError("invalid length argument")
	if (l < sub_length):
		raise ValueError("length argument too large")

	subsets = []
	for x in range(l - sub_length + 1):
		sub = []
		for y in range(sub_length):
			sub.append(atoi(input_string[x + y]))
		subsets.append(sub)
	return subsets
	
def largest_product(test_string,sub_length):
	limit = len(test_string)
	if (sub_length > limit):
		raise ValueError("length argument too large")

	max_product = 1
	for i in range(limit - sub_length + 1):
		product = 1
		sub_string = test_string[i:i + sub_length]
		
		for x in sub_string:
			product *= int(x)
			if (product > max_product):
				max_product = product
		
	return max_product
	
