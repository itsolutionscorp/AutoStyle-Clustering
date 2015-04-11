''' dna.py 
	created 25 Sept 2014
	by @jestuber
	'''

def to_rna(sequence):
	transcribe = {'G':'C','C':'G','T':'A','A':'U'}
	rna = ''
	for c in sequence:
		rna += transcribe[c]

	return rna
