'''
Created on 09/09/2013

@author: rodrigo
'''
try:
    from string import maketrans
except:
    maketrans = str.maketrans

class DNA(object):
    def __init__(self, string):
        self.string = string
        self.dna_string = 'ACGT'
        self.rna_string = 'ACGU'
        self.tableMap = maketrans(self.dna_string, self.rna_string)
        
    def to_rna(self):
        return self.string.translate(self.tableMap)
