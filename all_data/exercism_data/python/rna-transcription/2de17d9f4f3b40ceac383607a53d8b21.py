def to_rna(dna):
	dna.upper()
	#now transcribe
	rna = ''
	counter = 0
	dna_len = len(dna)
	
	while counter < dna_len:
		rna = rna + transcribe(dna[counter])
		counter+=1
	
	return rna

def transcribe(nucleotide):
	if nucleotide == 'G':
		return 'C'
	elif nucleotide == 'C':
		return 'G'
	elif nucleotide == 'T':
		return 'A'
	elif nucleotide == 'A':
		return 'U'
	else:
		return ''
