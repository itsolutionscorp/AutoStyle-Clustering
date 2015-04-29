def to_rna(dna):
	'''
	Input(s): dna string
	Output(s): transcribed rna string
	'''

	rna = []

	for i in range(len(dna)):

		if (dna[i] == 'G'):
			rna.append('C')
		elif(dna[i] == 'C'):
			rna.append('G')
		elif(dna[i] == 'T'):
			rna.append('A')
		elif(dna[i] == 'A'):
			rna.append('U')

	return ''.join(rna)
