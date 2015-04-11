class DNA(object):
    """Class representing DNA strand."""
    def __init__(self, strand):
        self.strand = strand

    def hamming_distance(self, strand):
        """Method calculating Hamming distance between object's DNA strand
        and given one."""
        n = min(len(self.strand), len(strand))
        k = 0

        for i in range(0, n):
            if self.strand[i] != strand[i]:
                k += 1
        return k
