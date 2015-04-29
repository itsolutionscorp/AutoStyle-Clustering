def to_rna(seq):
	#make dicitonary to easily get rna BP from dna BP by using dictionary.get()
	#use translater BPs to form list and then return as string
	basepairs = {'G':'C','C':'G','T':'A','A':'U'}
	rna = [basepairs.get(x) for x in seq.upper()]
	return ''.join(rna)
