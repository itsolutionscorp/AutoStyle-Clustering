#!/usr/bin/env python

xlate = {
        'C' : 'G',
        'G' : 'C',
        'T' : 'A',
        'A' : 'U'
        }

def to_rna(seq):
    ret = map(lambda x: xlate[x], seq)
    return "".join(ret)
