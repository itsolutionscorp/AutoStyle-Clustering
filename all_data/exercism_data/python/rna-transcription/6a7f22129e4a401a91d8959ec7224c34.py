# -*- coding: utf-8 -*-

def to_rna(dna_chain):
    dic = {ord('G'):'C', ord('C'):'G', ord('T'):'A', ord('A'):'U'}
    return dna_chain.translate( dic )
