from string import maketrans

class DNA(object):
    transcriber = maketrans('GCTA', 'CGAU')

    def __init__(self, strand):
        self.strand = strand

    def to_rna(self):
        return self.strand.translate(self.transcriber)
