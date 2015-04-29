class DNA:
    def __init__(self, s):
        # There is no copy here, to give the user the option of whether or not 
        # to copy the data. If they do, they copy the data on their end, for 
        # now.
        self.data = s

    def to_rna(self):
        return ''.join(map(_nt_dna2rna, self.data))

# Converts a single nucleotide from DNA to RNA.
def _nt_dna2rna(n):
    if n == 'G':
        return 'C'
    if n == 'C':
        return 'G'
    if n == 'T':
        return 'A'
    if n == 'A':
        return 'U'
    else:
        raise ValueError('Input is not a DNA nucleotide.')
