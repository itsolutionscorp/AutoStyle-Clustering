#!/usr/bin/python
def to_rna(dna_string):
    dna_trans_dict = dict(G='C', C='G', T='A', A='U')
    translated_list = []
    for dna_char in dna_string:
        translated_list.append(dna_trans_dict[dna_char])
    return "".join(translated_list)
