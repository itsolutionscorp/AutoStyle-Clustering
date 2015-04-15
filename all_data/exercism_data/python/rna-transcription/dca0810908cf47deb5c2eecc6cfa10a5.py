# Dirk Herrmann's solution version 4 for exercism exercise "RNA Transcription"

# This file implements class DNA:
#
# * An object of type DNA is constructed by passing a string representing a
#   DNA strand, consisting only of characters "G", "C", "T" and "A"
#   representing DNA nucleotides.
#
# * The method DNA.to_rna returns a freshly allocated string representing the
#   RNA complement of the DNA strand, consisting only of characters "C", "G",
#   "A" and "U".
#
#   Note that to_rna returns a sequence of nucleotides that represent the
#   result of an RNA transcription.  An RNA transcription takes a DNA template
#   strand and forms an RNA strand where at each position the respective
#   /complementary/ base to the original DNA base is added.  For this reason,
#   the code translates from "GCTA" to "CGAU" instead of to "GCUA".
#
# Examples can be found in the test suite in rna_transcription_test.py.

import string

class DNA(object):
   _dnaNucleotides = "GCTA"
   _rnaComplements = "CGAU"
   _toRnaTranslationTable = string.maketrans(_dnaNucleotides, _rnaComplements)

   def __init__(self, dnaStrand):
      self.dnaStrand = dnaStrand

   def to_rna(self):
      rnaStrand = self.dnaStrand.translate(DNA._toRnaTranslationTable)
      return rnaStrand
