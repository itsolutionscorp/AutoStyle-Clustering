# -*- coding: utf-8 -*-
"""
Created on Thu Sep 19 20:04:11 2013

@author: lunactic
"""

class DNA:
    rna = None
    def __init__(self, rna):
        self.rna = rna
    def to_rna(self):
        return self.rna.replace('T','U')
        
