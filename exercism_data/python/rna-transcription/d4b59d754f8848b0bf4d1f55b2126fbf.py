def to_rna(dna):
    _map = dict(zip('GCTA', 'CGAU'))
    return ''.join(map(lambda d:_map.get(d), dna))
