DNA_TO_RNA_COMPLEMENT = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}

def to_rna(nucs):
    return ''.join(DNA_TO_RNA_COMPLEMENT[n] for n in nucs)
