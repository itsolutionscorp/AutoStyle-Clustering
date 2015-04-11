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

    #create lookup
    complements = {'G': 'C', 'C': 'G','T': 'A', 'A': 'U'}
    
    return ''.join([complements[x] for x in sequence if x in complements.keys()])
     
