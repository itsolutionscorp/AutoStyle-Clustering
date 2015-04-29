__author__ = 'Adrian Rivera'


from string import maketrans


mapping = maketrans('GCTA', 'CGAU')


class DNA(object):

    def __init__(self, strand):
        self.strand = strand

    def to_rna(self):
        return self.strand.translate(mapping)
