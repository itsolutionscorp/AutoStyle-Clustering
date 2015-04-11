class DNA:
    def __init__(self, s):
        self.strand = s

    # Transcribe DNA to RNA
    #
    # * `G` -> `C`
    # * `C` -> `G`
    # * `T` -> `A`
    # * `A` -> `U`
    def to_rna(self):
        rna = []

        for nucleotide in self.strand:
            if nucleotide == 'G':
                rna.append('C')
            elif nucleotide == 'C':
                rna.append('G')
            elif nucleotide == 'T':
                rna.append('A')
            elif nucleotide == 'A':
                rna.append('U')

        return ''.join(rna)
