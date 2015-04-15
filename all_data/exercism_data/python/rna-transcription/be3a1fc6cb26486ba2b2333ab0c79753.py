converter = dict(G="C", C="G", T="A", A="U")

def to_rna(DNA):
	return "".join([converter[i] for i in DNA])
