converter = dict(G="C", C="G", T="A", A="U")

def to_rna(dna):
	return "".join([converter[i] for i in dna])
