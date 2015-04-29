DNA_TO_RNA_DICT = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}

def to_rna(dna_nucleotides):
    rna_complements = ''
    for dna_nucleotide in dna_nucleotides:
        if dna_nucleotide in DNA_TO_RNA_DICT:
            rna_complements += DNA_TO_RNA_DICT[dna_nucleotide]
        else:
            raise ValueError('"{0}" does not have RNA complement.'.format(dna_nucleotide))
    return rna_complements
