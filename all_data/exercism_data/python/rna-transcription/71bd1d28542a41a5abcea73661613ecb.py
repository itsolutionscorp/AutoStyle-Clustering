#!/usr/bin/python


def to_rna(string):
    """Convert any DNA strand in its corresponding RNA"""
    new_string = []
    for base in string:
        if base == 'G':
            new_base = 'C'
        if base == 'C':
            new_base = 'G'
        if base == 'T':
            new_base = 'A'
        if base == 'A':
            new_base = 'U'
        new_string.append(new_base)
    return ''.join(new_string)
