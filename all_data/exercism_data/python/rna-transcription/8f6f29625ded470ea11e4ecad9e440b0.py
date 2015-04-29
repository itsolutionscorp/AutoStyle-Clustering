import re
import string

class DNA(str):
    _to_rna_trans = string.maketrans('ACGT', 'ACGU')

    def __new__(cls, s):
        if not re.match('^[ACGT]*$', s):
            raise ValueError(s)
        return str.__new__(cls, s)

    def to_rna(self):
        return string.translate(self, self._to_rna_trans)
