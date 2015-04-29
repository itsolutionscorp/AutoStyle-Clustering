# -*- coding: utf-8 -*-
# By Nico Gevers (@gevious)

def to_rna(sequence):
    m = len(sequence)

    # we use an intermediate 'X' for C-G
    # we can improve this by ensuring only GCAT values exist in the initial
    # string
    return sequence \
        .replace('G', 'X', m) \
        .replace('C', 'G', m) \
        .replace('X', 'C', m) \
        .replace('A', 'U', m) \
        .replace('T', 'A', m)
