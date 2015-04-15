def to_rna(str):
	# dna to rna dictionary
	d_to_r = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
	outStr = ''

	#form output rna struct
	for char in str:
		outStr = outStr + d_to_r [char]
	return outStr
