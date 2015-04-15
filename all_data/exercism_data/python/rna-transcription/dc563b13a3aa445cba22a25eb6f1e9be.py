def to_rna(dna_strand):
    # DNA -> RNA nucleotides map
    dna_2_rna = {
        'G': 'C',
        'C': 'G',
        'T': 'A',
        'A': 'U',
    }

    # construct iterate over dna_strand and found its complements
    # then join them to the string and return as translated RNA
    return ''.join(dna_2_rna[dna_nucl] for dna_nucl in dna_strand)
