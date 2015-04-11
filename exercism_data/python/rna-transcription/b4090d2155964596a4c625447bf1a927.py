DNA_TO_RNA = {
    'G': 'C',
    'C': 'G',
    'T': 'A',
    'A': 'U'
	}


def to_rna(dna_strand):
    rna_strand = ''
    for base in dna_strand.upper():
        if base in DNA_TO_RNA:
            rna_strand = rna_strand + DNA_TO_RNA[base]
    return rna_strand
