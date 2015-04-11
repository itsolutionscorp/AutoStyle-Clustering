import string
class DNA:
    _tr=string.maketrans("GCTA", "CGAU")
    def __init__(self, code):
        self.code=code
    def to_rna(self):
        return string.translate(self.code, self._tr)
