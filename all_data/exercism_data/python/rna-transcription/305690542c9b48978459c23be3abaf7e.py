def to_rna(DNA):
	'''Given DNA strand return its RNA complement using transcription table:
	* `G` -> `C`
	* `C` -> `G`
	* `T` -> `A`
	* `A` -> `U`

	Arguments:
		DNA -- string representing DNA strand
		
	Returns: string representing complementing RNA strand
		
	'''
	translation = {
		'G':'C',
		'C':'G',
		'T':'A',
		'A':'U',
		}
	return ''.join(translation[ch] for ch in DNA)

from string import maketrans

def to_rna_2(DNA):
	'''Given DNA strand return its RNA complement using transcription table:
	* `G` -> `C`
	* `C` -> `G`
	* `T` -> `A`
	* `A` -> `U`

	Arguments:
		DNA -- string representing DNA strand
		
	Returns: string representing complementing RNA strand
		
	'''
	intab = "GCTA"
	outtab = "CGAU"
	trantab = maketrans(intab, outtab)
	return DNA.translate(trantab)
