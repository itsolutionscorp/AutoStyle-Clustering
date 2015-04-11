"""
* `G` -> `C`
* `C` -> `G`
* `T` -> `A`
* `A` -> `U`
"""

from string import maketrans


def to_rna(dna):
    guard = maketrans(u"GCTA", u"    ")
    proper = maketrans(u"GCTA", u"CGAU")
    if len(dna.translate(guard).strip()) == 0:
        return dna.translate(proper).strip()
    return dna
