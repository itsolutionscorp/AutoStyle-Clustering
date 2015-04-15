from string import maketrans
class DNA:

    @staticmethod
    def to_rna(dnastring):
        in = 'GCTA'
        out = 'CGAU'
        trans = maketrans(in, out)
        return dnastring.translate(trans)
