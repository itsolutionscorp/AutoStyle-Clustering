# This function determines the Hamming distance between two strings of DNA
# of equal length.

def distance(str1, str2):
	count = 0



	for x , y in zip(str1, str2):
		if x != y:
			count += 1
	
	return count



