# -*- coding: utf-8 -*-

# Rna Transcription
# .. see README.md for full description
#
#Given a DNA strand, its transcribed RNA strand is formed by replacing
#each nucleotide with its complement:
#
#* `G` -> `C`
#* `C` -> `G`
#* `T` -> `A`
#* `A` -> `U`
#
def to_rna(sequence):

    rna = ""
    #create lookup
    complements = {'G': 'C', 'C': 'G','T': 'A', 'A': 'U'}
    
    if sequence:
        for nuc in sequence:
            #ignore invalid nucleotides
            if nuc in complements:
                rna += complements[nuc]
            
    return rna
