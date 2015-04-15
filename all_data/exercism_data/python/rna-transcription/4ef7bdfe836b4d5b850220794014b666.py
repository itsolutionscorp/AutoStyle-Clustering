"RNA Transcription exercise"

def dna_to_rna(dna_str):
	"""
	Translate a DNA string to the equivalent RNA string,
	The four nucleotides found in DNA are adenine (A), cytosine (C),
	guanine (G) and thymidine (T).
	The four nucleotides found in RNA are adenine (A), cytosine (C),
	guanine (G) and uracil (U).
	"""
	return dna_str.replace('T', 'U')

class DNA(object):
	"A DNA strand."

	def __init__(self, dna_str):
		self.dna = dna_str

	def to_rna(self):
		"Translate to the equivalent RNA string"
		return dna_to_rna(self.dna)
