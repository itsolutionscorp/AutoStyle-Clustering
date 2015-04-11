def to_rna(DNA_strand):
    '''For each nucleotide in a DNA nucleotide strand,
       return the RNA complement nucleotide.'''
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
    # Return RNA as string created from the list of RNA nucleotides.
    return ''.join(RNA)
