'''
Given a DNA strand, its transcribed RNA strand is formed by replacing
each nucleotide with its complement:

* `G` -> `C`
* `C` -> `G`
* `T` -> `A`
* `A` -> `U`
'''

def to_rna(seq):
    '''
    Converts DNA sequences into the RNA complement
    '''
    complement = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}
    result = ''
    for protein in seq:
        result += complement[protein]
    
    return result
