'''
RNA Transcription
'''

def to_rna(dna):
	#Dictionary of transcription
	transcribe = dict(zip(['G', 'C', 'T', 'A'],['C', 'G', 'A', 'U']))
	
	#Return transcription
	return "".join([transcribe[nucleotide] for nucleotide in dna])
	
