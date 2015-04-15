def to_rna(dna):
	rna = ''
	
	for position in dna:
		rna += transcribe(position)
		
	return rna

def transcribe(nt):
	if nt == 'A':
		return 'U'
		
	if nt == 'C':
		return 'G'
		
	if nt == 'G':
		return 'C'
	
	if nt == 'T':
		return 'A'
