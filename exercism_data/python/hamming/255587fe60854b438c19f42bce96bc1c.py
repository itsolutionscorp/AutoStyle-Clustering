def distance(strandA, strandB):
	''' Calculates the Hamming difference between two DNA strands

	Arguments:
	- strandA - string representing DNA strand
	- strandB - string representing DNA strand of the same lenght as strandA

	Returns:
	- integer representing Hamming distance

	'''
	# Raise exception if arguments ar not of the same lenght
	if len(strandA) != len(strandB):
		raise ValueError("Arguments must be of the same length")
	# Return the number of different nucleotides in both strands
	return len(filter(lambda (x,y): x!=y, zip(strandA,strandB)))
	
	# Alternative version
	#return sum(i != j for i,j in zip(strandA,strandB))


# Standalone debugging
if __name__ == '__main__':
	print(distance('GGACGGATTCTG', 'AGGACGGATTCT'))
