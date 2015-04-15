#!/usr/bin/env python2
# -*- coding: utf-8 -*-

def to_rna(seq):
    d_to_r = { 'G':'C', 'C':'G', 'T':'A', 'A':'U' }
    return ''.join([d_to_r[x] for x in seq])

if __name__ == '__main__':
    print '%s' % ('This script is meant to be used this way.')
