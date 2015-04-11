from string import maketrans

class DNA:
	TO_RNA_TABLE = maketrans('GCTA','CGAU')

	def __init__(self, strand):
		self.strand = strand

	def to_rna(self):
		return self.strand.translate(DNA.TO_RNA_TABLE)
