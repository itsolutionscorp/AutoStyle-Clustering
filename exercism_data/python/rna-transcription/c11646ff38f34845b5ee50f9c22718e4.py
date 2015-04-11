"""
* `G` -> `C`
* `C` -> `G`
* `T` -> `A`
* `A` -> `U`
"""

def to_rna(dnaString):
	rnaString = ""
	for dnaChar in dnaString:
		if dnaChar == 'G':
			rnaString += 'C'
		elif dnaChar == 'C':
			rnaString += 'G'
		elif dnaChar == 'T':
			rnaString += 'A'
		elif dnaChar == 'A':
			rnaString += 'U'
	return rnaString
