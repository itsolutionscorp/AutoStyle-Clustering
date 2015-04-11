TRANSCRIBE = dict(zip('GCTA','CGAU'))
 
def to_rna(sequence):
	return ''.join(map(lambda x:TRANSCRIBE[x],sequence))
