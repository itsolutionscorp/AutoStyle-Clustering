#!/bin/python

class DNA:
    dna = ""
    def __init__(self, dna):
        self.dna = dna

    def to_rna(self):
        dna = list(self.dna)
        rna = [] 
        for i in dna:
            if "C" in i:
                rna.append('G')
            elif "G" in i:
                rna.append('C')
            elif "T" in i:
                rna.append('A')
            elif "A" in i:
                rna.append('U')
        return "".join(rna) 
