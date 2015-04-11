#!/usr/bin/python

def to_rna(seq):
  
  translation = {"C":"G", "A":"U", "T":"A", "G":"C"}
     
  return ''.join(translation[letter] for letter in seq)
