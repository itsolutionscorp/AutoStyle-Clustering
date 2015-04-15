"""A DNA to RNA simulator. Given a string of valid DNA base-pairs
(G,C,T,A), the function returns a string representing the corresponsing
RNA base-pairs (C,G,A,U)"""

def to_rna(dna):
    """String of DNA nucleoide => Strong of RNA nucleoide"""
    dna_rna_nucleotide_map = {'G':'C',
                              'C':'G',
                              'T':'A',
                              'A':'U'}
    rna = []
    for nucleotide in dna:
        rna.append(dna_rna_nucleotide_map[nucleotide])
    return ''.join(rna)
