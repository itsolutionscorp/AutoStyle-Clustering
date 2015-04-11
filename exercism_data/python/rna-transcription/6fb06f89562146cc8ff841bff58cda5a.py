import string


class DNA(object):
    # `G` -> `C`
    # `C` -> `G`
    # `T` -> `A`
    # `A` -> `U`
    _translation = string.maketrans('GCTA', 'CGAU')

    def __init__(self, strand):
        self.strand = strand

    def to_rna(self):
        return self.strand.translate(self._translation)
