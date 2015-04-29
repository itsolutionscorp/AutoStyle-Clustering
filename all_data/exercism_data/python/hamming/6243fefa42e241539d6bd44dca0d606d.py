def same_size(one, two):
	if len(one) != len(two):
	
		return 1
	

def distance(one, two):
	if same_size != 0:

		count = 0
	
	for i in range(len(one)):
		if one[i] != two[i]:
	
			count += 1
	return count
