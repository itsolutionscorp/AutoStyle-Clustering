def to_rna(dna_strand):
	"""
	Given a DNA strand, return its RNA complement
	(per RNA transcription)
	"""
	from string import maketrans
	dna_comp = "GCTA"
	rna_comp = "CGAU"
	return dna_strand.translate(maketrans(dna_comp,rna_comp))
