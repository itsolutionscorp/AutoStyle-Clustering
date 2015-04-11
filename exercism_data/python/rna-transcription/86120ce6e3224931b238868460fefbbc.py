# dna.py

def to_rna(dna_sequence):
	'''
	Substitute the letters in a DNA sequence to their corresponding
	letters in an RNA sequence.

	There are some fancier ways to do this, translation, regex, dictionaries...

	Since this was so short I just did simple replacements to lowercase and then returned uppercase
	'''

	# replace text to lowercase
	dna_sequence = dna_sequence.replace('G', 'c')
	dna_sequence = dna_sequence.replace('C', 'g')
	dna_sequence = dna_sequence.replace('T', 'a')
	dna_sequence = dna_sequence.replace('A', 'u')

	# return uppercase
	return dna_sequence.upper()
