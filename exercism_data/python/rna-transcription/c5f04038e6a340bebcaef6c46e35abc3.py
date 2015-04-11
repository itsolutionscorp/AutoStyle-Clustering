DNA_NUCLEOTIDES = ('A', 'C', 'G', 'T')
RNA_NUCLEOTIDES = ('A', 'C', 'G', 'U')

class DNA(object):
	def __init__(self, nucleotides):
		self.nucleotides = nucleotides
		
	def to_rna(self):
		return ''.join([self.convert_dna_nucleotide_to_rna(nucleotide) for nucleotide in self.nucleotides])
		
	def convert_dna_nucleotide_to_rna(self, nucleotide):
		return RNA_NUCLEOTIDES[DNA_NUCLEOTIDES.index(nucleotide)]
	
