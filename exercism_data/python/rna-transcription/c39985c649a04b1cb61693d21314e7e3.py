#!/usr/bin/python
class DNA(object):
    """Fancy thing."""
    def __init__(self, dna):
        self.dna = dna

    def to_rna(self):
        """Whoever came up with this RNA thing is crazy."""
        return self.dna.replace('T', 'U')
