def to_rna(dna):
	"""Return the RNA complement of a DNA strand."""
	rna = []
	transcription = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}

	for nucleotide in dna:
			rna += transcription.get(nucleotide, '')

	return ''.join(rna)


# Author's Notes on Coding Conventions:

	# I've coded to pep8 conventions with a few exceptions that
	# work nicely with my setup (I'm only coding for myself):
		# ignore = W191,W293		- indentation contains tabs, blank line contains whitespace
		# max-line-length = 120

# CONSIDERED:

	# Originally had rna as a string, but sorceforge pointed out string immutability meant each
	# rna += ... created a new string. For long strings most of the processing could be spent
	# doing object creation and deletion.

# Rna Transcription

# Write a program that, given a DNA strand, returns its RNA complement (per RNA transcription).

# Both DNA and RNA strands are a sequence of nucleotides.

# The four nucleotides found in DNA are adenine (**A**), cytosine (**C**),
# guanine (**G**) and thymidine (**T**).

# The four nucleotides found in RNA are adenine (**A**), cytosine (**C**),
# guanine (**G**) and uracil (**U**).

# Given a DNA strand, its transcribed RNA strand is formed by replacing
# each nucleotide with its complement:

# * `G` -> `C`
# * `C` -> `G`
# * `T` -> `A`
# * `A` -> `U`
