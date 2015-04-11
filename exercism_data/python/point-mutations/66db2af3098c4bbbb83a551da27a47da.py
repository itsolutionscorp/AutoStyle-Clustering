'''
	Represents a DNA class that contains an attribute "strand"
'''
class DNA(object):
	'''
		Initialize the value of the strand
	'''
	def __init__(self, strand):
		self.strand = strand

	'''
		The hamming distance function that takes in another strand
	'''
	def hamming_distance(self, other_strand):
		return self.hamming_helper(other_strand, self.strand) if len(self.strand) > len(other_strand) else self.hamming_helper(self.strand, other_strand)

	'''
		The helper function that takes in two strands: a smaller and the larger strand
	'''
	def hamming_helper(self, min_strand, max_strand):
		hamming_distance = index = 0

		# For each nucleotide in the strands, compare if they are equal
		while index < len(min_strand):
			# if not equal, increment the hammming distance
			if min_strand[index] != max_strand[index]:
				hamming_distance += 1
			index += 1
		return hamming_distance
