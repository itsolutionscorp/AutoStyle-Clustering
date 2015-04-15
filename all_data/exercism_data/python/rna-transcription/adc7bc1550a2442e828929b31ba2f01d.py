"""
Given a DNA strand, its transcribed RNA strand is formed by replacing
each nucleotide with its complement:

* `G` -> `C`
* `C` -> `G`
* `T` -> `A`
* `A` -> `U`
"""

DNA_TO_RNA = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}

def to_rna(dna_strand):
    rna_strand = ''.join([DNA_TO_RNA[nuc] for nuc in dna_strand])
    return rna_strand
