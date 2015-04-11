#!/usr/bin/python
def to_rna(sequence):
    mapping = dict(zip('GCTA', 'CGAU'))
    return "".join([mapping[x] for x in sequence])
