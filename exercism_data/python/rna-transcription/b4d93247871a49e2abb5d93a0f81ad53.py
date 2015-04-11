def to_rna(DNA):
	transcription={'G':'C','C':'G','T':'A','A':'U'}
	RNA=[transcription[letter] for letter in DNA]
	return ''.join(RNA)
