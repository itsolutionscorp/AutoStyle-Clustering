'''
RNA Transcription
'''

def to_rna(dna):
	#Dictionary of transcription
	table = str.maketrans("GCTA", "CGAU")
	
	#Return transcription
	return dna.translate(table)
	
