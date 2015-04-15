###################################
# Function determines the hamming distance between
#		two dna sequences of equal length
# inputs: string, string
# returns: The number of differences between 
#			two homologous DNA strands.
#		or -1 if the strands are different lengths		
def distance(one, two):
	one = list(one)
	two = list(two)
	
	if len(one) != len(two):
		return -1
	
	count = 0
	for i in range(len(one)):
		if one[i] != two[i]:
			count += 1
	
	return count
