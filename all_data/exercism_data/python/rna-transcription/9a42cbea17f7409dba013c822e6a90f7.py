#Program for rna_transcription_text.py 

def to_rna(dna):
	#Turn the DNA string into a list and then replace with the complement 
	dnaChar = list(dna)
	for i, c in enumerate(dnaChar):
		if c == 'A':
			dnaChar[i] = 'U'
		elif c == 'T':
			dnaChar[i] = 'A'
		elif c == 'C':
			dnaChar[i] = 'G'
		elif c == 'G':
			dnaChar[i] = 'C'

	return ''.join(str(x) for x in dnaChar)
