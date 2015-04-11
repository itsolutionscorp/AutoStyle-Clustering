#!/usr/bin/env python

class DNA():
    compl = str.maketrans(
        {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'})
    
    def __init__(self, sequence):
        self.sequence = sequence
        self.rna = None

    def to_rna(self):
        if self.rna is None:
            self.rna = self.sequence.translate(self.compl)
        return self.rna
    
    
        
