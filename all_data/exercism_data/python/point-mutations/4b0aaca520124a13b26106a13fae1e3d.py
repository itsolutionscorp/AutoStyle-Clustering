class DNA:
    def __init__(self, nucleotides):
        self.nucleotides = nucleotides

    def hamming_distance(self, nucleotides):
        return len(self.differences(nucleotides))

    def differences(self, nucleotides):
        return [nucleotide_pair[0] for nucleotide_pair in zip(self.nucleotides, nucleotides) if nucleotide_pair[0] != nucleotide_pair[1]]
