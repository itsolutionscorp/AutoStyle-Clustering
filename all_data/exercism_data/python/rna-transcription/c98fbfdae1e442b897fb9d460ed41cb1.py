#!/usr/bin/python2

trans_db = {"G": "C", "C": "G", "T": "A", "A": "U"}


def to_rna(strand):
    rna_seq = ""
    for nct in strand:
        rna_seq += trans_db[nct]
    return rna_seq
