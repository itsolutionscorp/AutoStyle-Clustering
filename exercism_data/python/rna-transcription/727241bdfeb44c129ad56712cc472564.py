# Author::  Tim Henigan
# Copyright:: Copyright (c) 2013
# License::   MIT (http://opensource.org/licenses/MIT)

class DNA(object):
    """The DNA class represents and manipulates DNA strings."""

    def __init__(self, dna):
        self.dna = dna.strip().upper()
        assert set(self.dna) <= set("ACGT")

    def to_rna(self):
        """Translate the DNA string to its equivalent RNA string."""
        return self.dna.replace('T', 'U')
