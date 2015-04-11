'''
	This class represents a DNA class
	It consists of 3 attributes:
		1) A string representation of its strand
		2) A map representing the count of each nucleotide
		3) An array/list containing the possible nucleotides allowed
'''
class DNA(object):
	'''
		Initializes the DNA instance with a given strand
		Creates an empty map of nucleotides
	'''
	def __init__(self, strand):
		self.strand = strand
		self.std_nucleotides = ['A', 'C', 'G', 'T', 'U']
		self.nucleotide_map = {'A': 0, 'C': 0, 'G': 0, 'T': 0}
		self.init_map()

	'''
		Given a DNA strand, update the nucleotide map with latest counts
	'''
	def init_map(self):
		for nucleotide in self.strand:
				self.nucleotide_map[nucleotide] += 1

	'''
		Given a nucleotide i.e. A, C, G, T
		return the count of such nucleotide inside the DNA strand
		If an invalid nucleotide is provided, raise an exception
	'''
	def count(self, nucleotide):
			if nucleotide not in self.std_nucleotides:
				raise ValueError (nucleotide + ' is not a nucleotide.')
			else:
				return 0 if nucleotide not in self.nucleotide_map else self.nucleotide_map[nucleotide]

	'''
		Simple return the updated map of nucleotide counts
	'''
	def nucleotide_counts(self):
		return self.nucleotide_map
