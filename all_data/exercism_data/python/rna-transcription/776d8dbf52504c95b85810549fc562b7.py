from string import maketrans

_translator = maketrans('GCTA', 'CGAU')


def to_rna(dna_stream):
    return dna_stream.translate(_translator)
