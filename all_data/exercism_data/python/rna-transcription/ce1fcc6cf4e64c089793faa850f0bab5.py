def to_rna(expr):
	'''
	Function to return the RNA compliment
	of a DNA input string
	'''

	# set up a dictionary for DNA -> RNA 
	trans = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}

	# set up the return string
	rna = ''

	# do the translation
	for c in expr:
		# double check it's a valid DNA input
		if c in trans:
			rna += trans[c]

	return rna
