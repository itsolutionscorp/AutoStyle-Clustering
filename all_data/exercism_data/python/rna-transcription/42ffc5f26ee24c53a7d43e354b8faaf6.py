#dna module

def to_rna(DNA_strand):
    '''
    Given a DNA strand, its transcribed RNA strand is formed by replacing
    each nucleotide with its complement:

    * `G` -> `C`
    * `C` -> `G`
    * `T` -> `A`
    * `A` -> `U`

    :param DNA_strand: String
    :return: String
    '''

    RNA_strand = []

    for chr in DNA_strand:
        if chr == 'G':
            RNA_strand.append('C')
        elif chr == 'C':
            RNA_strand.append('G')
        elif chr == 'T':
            RNA_strand.append('A')
        elif chr == 'A':
            RNA_strand.append('U')

    return ''.join(RNA_strand)
