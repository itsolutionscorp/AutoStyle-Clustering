def to_rna(dna):

	#Transcription dictionary
	rna_transcribe = {}
	rna_transcribe['C'] = 'G'
	rna_transcribe['G'] = 'C'
	rna_transcribe['T'] = 'A'
	rna_transcribe['A'] = 'U'
	
	#list of RNA nucleotides 
	rna = []

	for nucleotides in dna:
		rna.append(rna_transcribe[nucleotides])

	#convert to string to output
	return "".join(rna)
