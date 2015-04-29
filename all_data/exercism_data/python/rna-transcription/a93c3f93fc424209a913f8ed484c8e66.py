#!/usr/bin/python

def to_rna(seq):
  
  translation = {"C":"G", "A":"U", "T":"A", "G":"C"}
  
  out = ""
  
  for letter in seq:
    out = out + translation[letter]
    
  return out
