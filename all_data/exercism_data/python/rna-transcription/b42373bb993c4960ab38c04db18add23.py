# The entry method, which creates a DnaResult object containing a specifiec strand
def DNA(strand):
	result = DnaResult(strand)
	return result

# Represents a DNA strand
class DnaResult(object):

	# Initialize the strand attribute
	def __init__(self, strand):
		self.strand = strand

	# Replaces all thymidine nucleotides with uracil nucleotides
	def to_rna(self):
		t_count = self.strand.count('T')
		self.strand = self.strand.replace('T', 'U', t_count)
		return self.strand
