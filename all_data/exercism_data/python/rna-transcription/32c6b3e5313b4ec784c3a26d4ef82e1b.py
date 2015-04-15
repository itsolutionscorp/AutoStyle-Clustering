# -*- coding: utf-8 -*-
from string import maketrans

def to_rna(dna):
  # Short oneliners rule
  return dna.translate(maketrans('GCTA', 'CGAU'))
