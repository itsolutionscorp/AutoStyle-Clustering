import string

def to_rna(dna):
    '''Given a DNA strand, its transcribed RNA strand is formed by replacing
    each nucleotide with its complement:

    * `G` -> `C`
    * `C` -> `G`
    * `T` -> `A`
    * `A` -> `U`
    '''
    return dna.translate(string.maketrans('GCTA', 'CGAU'))
