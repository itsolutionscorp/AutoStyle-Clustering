#!/usr/bin/env python3
# -*- coding: utf-8 -*-

# Submission file for the python rna_transcription_test exercise.
#
# v2: uses maketrans from python3 as seen in @sqrt iteration 2
# v1: dict map and list processing with string join, includes error checks
#     spaced for readability purposes

def to_rna(dna):
  '''
  Accept DNA string, return RNA string
  '''
  table = str.maketrans('GCTA','CGAU')
  
  rna = dna.upper().translate(table)
  return rna
