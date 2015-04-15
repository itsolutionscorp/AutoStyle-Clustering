dna2rna = {
    'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U',
}

def to_rna(dna):
    rna = ""
    for bp in dna:
	rna += dna2rna[bp]	
    return rna
