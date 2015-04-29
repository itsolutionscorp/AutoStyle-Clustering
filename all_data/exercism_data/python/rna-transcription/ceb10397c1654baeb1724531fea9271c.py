TRANSCRIBE = dict(zip('GCTA','CGAU'))
 
def to_rna(sequence):
	global TRANSCRIBE
	if sequence in TRANSCRIBE:
		return TRANSCRIBE[sequence]
	return ''.join(map(to_rna,sequence))
