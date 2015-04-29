# Dirk Herrmann's solution version 3 for exercism exercise "RNA Transcription"

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
#   result of a RNA transcription.  RNA transcription takes a DNA template
#   strand and forms an RNA strand where at each position the respective
#   /complementary/ base to the original DNA base is added.  For this reason,
#   the code translates from "GCTA" to "CGAU" instead of to "GCUA".
#
#   Consequently, the name 'to_rna' is ambiguous.  A better name would have
#   been 'rna_transcription'.
#
# Examples can be found in the test suite in rna_transcription_test.py.

import string

class DNA:
   dnaNucleotides = "GCTA"
   rnaComplements = "CGAU"
   toRnaTranslationTable = string.maketrans(dnaNucleotides, rnaComplements)

   def __init__(self, dnaStrand):
      self.dnaStrand = dnaStrand

   def to_rna(self):
      rnaStrand = self.dnaStrand.translate(DNA.toRnaTranslationTable)
      return rnaStrand
