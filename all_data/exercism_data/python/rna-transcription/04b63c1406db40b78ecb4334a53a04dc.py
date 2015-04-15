#rna transcription

"""
*  dna -> rna
* `G` -> `C`
* `C` -> `G`
* `T` -> `A`
* `A` -> `U`
"""


def to_rna(input):
	dr = {"G":"C", "C":"G", "T":"A", "A":"U"}
	for char in input:
		input = input.replace(char, dr.get(char))
	return input
