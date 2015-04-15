# -*- coding: utf-8 -*-

def to_rna(dna):
    cypher = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
    return "".join([cypher[letter] for letter in dna])
