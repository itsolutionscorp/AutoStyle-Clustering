import string

_DNA_BASES = 'GCTA'
_RNA_BASES = 'CGAU'
_translate_table = str.maketrans(_DNA_BASES, _RNA_BASES)

class DNA(object):
    def __init__(self, bases):
        bases = bases.upper()
        if any((base not in _DNA_BASES) for base in bases):
            unrecognized = bases.translate(None, _DNA_BASES)
            raise ValueError("DNA base should be one of the %s, found %s."
                             %(_DNA_BASES, next(unrecognized)))
        self._bases = bases

    def to_rna(self):
        return self._bases.translate(_translate_table)
