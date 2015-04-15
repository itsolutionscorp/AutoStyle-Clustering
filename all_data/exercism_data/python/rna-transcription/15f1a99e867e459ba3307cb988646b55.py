def to_rna(DNA_strand):
    '''For each nucleotide in a DNA nucleotide strand,
       determine the RNA complement nucleotide. Return
       the RNA nucleotide sequence as a string.'''
    # Dict keys: DNA. Values: RNA
    DNA_RNA = {
        'G': 'C',
        'C': 'G',
        'T': 'A',
        'A': 'U',
    }
    RNA = []

    for DNA in DNA_strand:
        RNA.append(DNA_RNA[DNA])
    return ''.join(RNA)
