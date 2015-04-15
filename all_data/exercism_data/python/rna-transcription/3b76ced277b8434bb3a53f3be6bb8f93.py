__author__ = 'jetties'


def _nucleotide_compliment(nucleotide):
    """
    * `G` -> `C`
    * `C` -> `G`
    * `T` -> `A`
    * `A` -> `U`
    """
    if nucleotide == 'G':
        return 'C'
    elif nucleotide == 'C':
        return 'G'
    elif nucleotide == 'T':
        return 'A'
    elif nucleotide == 'A':
        return 'U'
    else:
        raise Exception('Unknown nucleotide')


def to_rna(dna):
    # Convert DNA to RNA; results in a list of chars.
    rna = map(_nucleotide_compliment, dna)
    return ''.join(rna)
