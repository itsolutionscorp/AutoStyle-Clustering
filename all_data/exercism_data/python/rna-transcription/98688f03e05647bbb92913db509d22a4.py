#!/usr/bin/env python

class DNA:
    def __init__(self, sequence):
        self.sequence = sequence
    
    def to_rna(self):
        return self.sequence.replace('T', 'U')
