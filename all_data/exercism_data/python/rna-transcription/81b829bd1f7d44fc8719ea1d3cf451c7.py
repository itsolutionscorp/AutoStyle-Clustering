from string import maketrans

class DNA(object):
    """Represents a DNA strand"""
    def __init__(self, dnaStrand):
        if not dnaStrand or dnaStrand.isdigit() or dnaStrand.isspace():
            raise Exception("dnaStrand is empty")
        self.dnaStrand = dnaStrand

    def to_rna(self):
        """Converts DNA to RNA
        * `G` -> `C`
        * `C` -> `G`
        * `T` -> `A`
        * `A` -> `U`
        """
        table = maketrans('GCTA','CGAU')
        rna = ""
        for x in self.dnaStrand:
            rna += x.translate(table)
        return rna
