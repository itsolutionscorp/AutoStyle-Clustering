class DNA(object):

    def __init__(self, dna_strand):
        self.dna_strand = dna_strand

    def to_rna(self):
        rna_strand = ''
        for nucleotide in self.dna_strand:
            if nucleotide.upper() in 'CGUA':
                rna_strand += nucleotide.upper()
            elif nucleotide.upper() == 'T':
                rna_strand += 'U'
        return rna_strand
