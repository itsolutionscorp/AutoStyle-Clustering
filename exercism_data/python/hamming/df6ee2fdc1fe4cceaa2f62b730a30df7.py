def hamming(seq1, seq2):
	"""Calculate the Hamming difference between two DNA strands."""

	if seq1 == seq2: return 0 			# Optimization.
										# May not be appropriate if few non-mutated sequences expected.
	hammingNumber = 0
	len1 = len(seq1)
	len2 = len(seq2)

	if len1 == len2: l = len2
	elif len1 > len2:
		l = len2
		hammingNumber = len1 - len2 	# Handle differences in length, as required by unit tests.
	elif len2 > len1:
		l = len1
		hammingNumber = len2 - len1 	# Handle differences in length, as required by unit tests.

	seq1 = list(seq1)
	seq2 = list(seq2)
	
	for x in range(0, l):
		if seq1[x] != seq2[x]: hammingNumber += 1
	
	return hammingNumber


# Author's Notes on Coding Conventions:

	# I've coded to pep8 conventions with a few exceptions that
	# work nicely with my setup (I'm only coding for myself):
		# ignore = W191,E261,W293,E701
		# max-line-length = 120


# # Hamming

# Write a program that can calculate the Hamming difference between two DNA strands.

# A mutation is simply a mistake that occurs during the creation or
# copying of a nucleic acid, in particular DNA. Because nucleic acids are
# vital to cellular functions, mutations tend to cause a ripple effect
# throughout the cell. Although mutations are technically mistakes, a very
# rare mutation may equip the cell with a beneficial attribute. In fact,
# the macro effects of evolution are attributable by the accumulated
# result of beneficial microscopic mutations over many generations.

# The simplest and most common type of nucleic acid mutation is a point
# mutation, which replaces one base with another at a single nucleotide.

# By counting the number of differences between two homologous DNA strands
# taken from different genomes with a common ancestor, we get a measure of
# the minimum number of point mutations that could have occurred on the
# evolutionary path between the two strands.

# This is called the 'Hamming distance'

#     GAGCCTACTAACGGGAT
#     CATCGTAATGACGGCCT
#     ^ ^ ^  ^ ^    ^^

# The Hamming distance between these two DNA strands is 7.

# # Implementation notes

# The Hamming distance is only defined for sequences of equal length. Hence you
# may assume that only sequences of equal length will be passed to your hamming
# distance function.
