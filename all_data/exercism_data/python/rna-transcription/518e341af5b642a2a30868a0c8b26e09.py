#/usr/bin/env python
def to_rna(dna):
	listi = list(dna)
	for cl in range(len(listi)):
		if listi[cl] == 'C':
			listi[cl] = 'G'
		elif listi[cl] == 'G':
			listi[cl] = 'C'
		elif listi[cl] == 'T':
			listi[cl] = 'A'
		elif listi[cl] == 'A':
			listi[cl] = 'U'
	stringi = ''.join(listi)
	return stringi
