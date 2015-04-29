#!/usr/bin/env python

def to_rna(sequence):
  key = {"G":"C",
         "C":"G",
         "T":"A",
         "A":"U",
        }
  translation = ""

  for i in range(len(sequence)):
    translation += key[sequence[i]]
  return translation
