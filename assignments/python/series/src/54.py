from string import *
def slices(input_string,sub_length):
	if (sub_length < 1):
		raise ValueError("invalid length argument")
		return
	l = len(input_string)
	if (l < sub_length):
		raise ValueError("length argument too large")
		return	
	subsets = []
	for x in range(l - sub_length + 1):
		sub = []
		for y in range(sub_length):
			sub.append(atoi(input_string[x + y]))
		subsets.append(sub)
	return subsets
