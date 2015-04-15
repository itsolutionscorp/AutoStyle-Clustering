def to_rna(DNA):
    ''' Swaps DNA bases to RNA complementary bases'''

    # a dictionary used to convert a DNA base to RNA
    DNA_to_RNA = {'A': 'U', 'C': 'G', 'G': 'C', 'T': 'A'}

    RNA = ''
    RNA = [ RNA + DNA_to_RNA[i] for i in DNA ]

    return ''.join(RNA)
