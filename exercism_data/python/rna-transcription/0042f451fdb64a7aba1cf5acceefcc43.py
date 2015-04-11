_complements = {
    'G' : 'C',
    'C' : 'G',
    'T' : 'A',
    'A' : 'U'
}


class TranscriptionError(Exception):
    pass


def _match(nucleotide):
    if nucleotide in _complements:
        return _complements[nucleotide]
    else:
        raise TranscriptionError("{} is not a DNA nucleotide".format(nucleotide))


def to_rna(strand):
    return ''.join(map(_match, strand))
    
