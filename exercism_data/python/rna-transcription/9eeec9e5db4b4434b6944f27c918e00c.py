### RNA - DNA transcription tests ###

def to_rna(DNA):
	transcription = {'A':'U', 'T':'A', 'G':'X', 'C':'Y'}
	transcription2 = {'X':'C', 'Y':'G'}
	for i, j in transcription.iteritems():
		DNA = DNA.replace(i, j)

	for i, j in transcription2.iteritems():
		DNA = DNA.replace(i, j)
	return DNA
			
