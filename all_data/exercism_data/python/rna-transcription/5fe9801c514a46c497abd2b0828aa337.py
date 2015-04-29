class DNA(object):
	def __init__(self, arg):
		super(DNA, self).__init__()
		self.dna_string = arg
	
	def to_rna(self):
		transcription = {'G': 'C', 'T': 'A', 'C': 'G', 'A': 'U'}
		return 	"".join([transcription[i] for i in self.dna_string])
