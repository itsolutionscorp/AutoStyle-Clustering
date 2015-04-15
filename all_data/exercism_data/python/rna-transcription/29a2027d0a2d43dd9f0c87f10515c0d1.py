def to_rna(dna):

	#Transcription dictionary
	rna_transcribe = {}
	rna_transcribe['C'] = 'G'
	rna_transcribe['G'] = 'C'
	rna_transcribe['T'] = 'A'
	rna_transcribe['A'] = 'U'
	
	#map all DNA to RNA
	rna = map(lambda x: rna_transcribe[x], dna)

	#convert to string to output
	return "".join(rna)
