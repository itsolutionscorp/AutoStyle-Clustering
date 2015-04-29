# -*- coding: utf-8 -*-
"""
Created on Tue Oct 28 00:14:05 2014

@author: DiCar
"""

def to_rna(dna):
    #Populate a dictionary for the DNA to RNA translation. 
    #The key is the DNA base, the value is the RNA base.
    dict = {"G" : "C", "C" : "G", "T" : "A", "A" : "U"}
    #make an empty list called to be populated with the translated code   
    translation = []
    #convert the input dna string into a list of individual bases
    bases = list(dna)
    #for each DNA base in the list bases, translate it to the RNA base using
    #the dictionary and then append that value to the list translation
    for base in bases:
        translation.append(dict[base])
    #concantenate the strings in the list translation to get the desired output
    return ''.join(translation)
