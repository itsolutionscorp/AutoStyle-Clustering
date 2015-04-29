def to_rna(dna):
	conv = {'G':'C','C':'G','A':'U','T':'A'}
	dna = dna.upper()
	return ''.join([conv[char] for char in dna])  

if __name__ == '__main__':
	to_rna('GCGATAT')
	
