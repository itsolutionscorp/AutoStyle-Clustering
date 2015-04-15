#!/usr/bin/env python


LOOKUP_TABLE = {'C': 'G', 'G': 'C', 'T': 'A', 'A': 'U'}


def transmute(base):
    if base not in LOOKUP_TABLE:
        raise Exception('Invalid base ' + base)
    return LOOKUP_TABLE[base]


def to_rna(strand):
    return ''.join([transmute(base) for base in strand])


# vim:syn=python:et:
