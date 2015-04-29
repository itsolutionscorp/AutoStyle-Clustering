def to_rna(dna_strand):
	"""
	Given a DNA strand, return its RNA complement
	(per RNA transcription)
	"""
	rna_compl = ""
	for nucleotide in dna_strand:
		if nucleotide == "G":
			rna_compl+="C"
		elif nucleotide == "C":
			rna_compl+="G"
		elif nucleotide == "T":
			rna_compl+="A"
		elif nucleotide == "A":
			rna_compl+="U"
	return rna_compl
