'''
Created on 09/09/2013

@author: rodrigo
'''

class DNA(object):
    def __init__(self, string):
        self.string = string

    def to_rna(self):
        return self.string.replace('T', 'U')
