## mapping of DNA nucleotides to RNA nucleotides
map = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}

def to_rna(strand):
	"""Changes a DNA nucleotide sequence into an RNA
	nucleotide sequence.

	Transforms string into a list, applies map
	to each letter in list, and then joins list back into
	a string.
	"""
	return "".join([map[letter] for letter in list(strand)])
