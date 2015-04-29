# -*- coding: utf-8 -*-
"""
Created on Fri Nov 07 21:51:38 2014

@author: Richard
"""

def to_rna(dna):
    base = ""
    if len(dna) == 1:
        if dna.lower() == "g":
            base = "C"
        elif dna.lower() == "c":
            base = "G"
        elif dna.lower() == "t":
            base = "A"
        elif dna.lower() == "a":
            base = "U"
    elif len(dna) > 1:
        for i in dna:
            if i.lower() == "g":
                base += "C"
            elif i.lower() == "c":
                base += "G"
            elif i.lower() == "t":
                base += "A"
            elif i.lower() == "a":
                base += "U"
            
    return base
