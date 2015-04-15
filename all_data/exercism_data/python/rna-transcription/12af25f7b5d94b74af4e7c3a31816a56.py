def to_rna (strDNA):
	dnaDict = {'G' : 'C', 'C' : 'G', 'T' : 'A', 'A' : 'U'}
	strRNA = ""
	for l in strDNA:
		strRNA += dnaDict.get(l)
	return strRNA
