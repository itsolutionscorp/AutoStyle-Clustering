"RNA Transcription exercise"

class NucleotideString(object):
	"A string of nucleotides"
	alphabet = "ACGTU"

	def __init__(self, nucleotide_string):
		self._string = str(nucleotide_string)
		if any((char not in self.alphabet for char in self._string)):
			raise ValueError('Valid nucleotides are: ' + self.alphabet)

	def __eq__(self, other):
		return str(self) == str(other)

	def __str__(self):
		return self._string

class RNA(NucleotideString):
	"""
	An RNA strand.

	The four nucleotides found in RNA are:
	adenine (**A**)
	cytosine (**C**)
	guanine (**G**)
	uracil (**U**)
	"""
	alphabet = "ACGU"

class DNA(NucleotideString):
	"""
	A DNA strand.

	The four nucleotides found in DNA are:
	adenine (**A**)
	cytosine (**C**)
	guanine (**G**)
	thymidine (**T**)
	"""
	alphabet = "ACGT"

	def to_rna(self):
		"""
		Translate a DNA string to the equivalent RNA string,
		by replacing all occurrences of thymidine with uracil.
		"""
		return RNA(str(self).replace('T', 'U'))
