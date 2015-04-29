# Dirk Herrmann's solution version 1 for exercism exercise "RNA Transcription"

# This file implements class DNA:
# * An object of type DNA is constructed by passing a string representing a
#   DNA strand, consisting only of characters "G", "C", "T" and "A"
#   representing DNA nucleotides.
# * The method DNA.to_rna returns a freshly allocated string representing the
#   RNA complement of the DNA strand, consisting only of characters "C", "G",
#   "A" and "U".
#
# Examples can be found in the test suite in rna_transcription_test.py.

mapDnaToRna = {
   'G':'C',
   'C':'G',
   'T':'A',
   'A':'U' }

class DNA:
   def __init__(self, dnaStrand):
      self.dnaStrand = dnaStrand

   def to_rna(self):
      rnaStrandAsList = [mapDnaToRna[dna] for dna in self.dnaStrand]
      rnaStrand = "".join(rnaStrandAsList)
      return rnaStrand
