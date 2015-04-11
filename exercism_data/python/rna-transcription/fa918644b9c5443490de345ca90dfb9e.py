#!/usr/bin/env python

class DNA(object):
    def __init__(self, in_dna):
        self._dna = in_dna

    def to_rna(self):
        return self.transcribe()

    def transcribe(self):
        """
        Transcribe RNA by replacing all occurrences of thymidine with uracil.
        """
        return self._dna.replace("T", "U")
