''' dna.py 
	created 25 Sept 2014
	by @jestuber
	'''

def to_rna(sequence):
	transcribe = {'G':'C','C':'G','T':'A','A':'U'}
	
	return ''.join([transcribe[c] for c in sequence])
