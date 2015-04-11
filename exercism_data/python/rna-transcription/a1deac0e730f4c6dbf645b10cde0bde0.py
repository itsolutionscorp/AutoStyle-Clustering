#rna transcription

"""
*  dna -> rna
* `G` -> `C`
* `C` -> `G`
* `T` -> `A`
* `A` -> `U`
"""

#this isn't working, returns improper sequence of letters somehow???

def to_rna(input):
	dr = {"G":"C", "C":"G", "T":"A", "A":"U"}
	for char in input:
		input = input.replace(char, dr.get(char))
	return input
