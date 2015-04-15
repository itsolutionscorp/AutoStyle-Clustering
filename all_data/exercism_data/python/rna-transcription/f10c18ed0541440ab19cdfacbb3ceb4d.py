def to_rna(dnaStr):
	#Assume correct input
	dnaStr = dnaStr.upper()
	translator = {'C' : 'G', 'G' : 'C', 'T' : 'A', 'A' : 'U'}
	rnaStr = ''
	for nucleotide in dnaStr:
		rnaStr+=translator[nucleotide]
	return rnaStr
