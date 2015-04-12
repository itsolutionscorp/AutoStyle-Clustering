def distance(seq1,seq2):
	'''
	Calculate the Hamming difference between two DNA strands
	'''
	if len(seq1)!=len(seq2):
		return False
	else:
		return sum([1 for ii in range(len(seq1)) if seq1[ii]!=seq2[ii]])
		