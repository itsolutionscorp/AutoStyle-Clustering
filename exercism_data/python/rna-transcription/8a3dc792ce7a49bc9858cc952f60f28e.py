# -*- coding: utf-8 -*-

def to_rna(source):
    dict = {'G':'C','C':'G','T':'A','A':'U'}
    ret = ''
    for w in list(source):
        ret += dict[w]
    return ret
