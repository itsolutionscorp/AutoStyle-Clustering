dna_nucleotides_to_rna = [('A', 'A'), ('G', 'G'), ('C', 'C'), ('T', 'U')]

class DNA(object):

    nucleotide_map = dict((dna, rna) for dna, rna in dna_nucleotides_to_rna)
        
    def __init__(self, nucleotide_sequence):
        self.nucleotide_sequence = nucleotide_sequence

    def to_rna(self):
        return "".join(self.nucleotide_map[n] for n in self.nucleotide_sequence)
