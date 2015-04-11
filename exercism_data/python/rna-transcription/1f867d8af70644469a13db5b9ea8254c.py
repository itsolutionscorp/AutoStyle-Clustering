class DNA(object):
    """A DNA strand"""

    _DNA_TO_RNA = {'G': 'C',
                   'C': 'G',
                   'T': 'A',
                   'A': 'U'}

    def __init__(self, strand):
        self.strand = strand

    def to_rna(self):
        """Return the RNA complement to this DNA strand"""
        return "".join(self._DNA_TO_RNA[nt] for nt in self.strand)
