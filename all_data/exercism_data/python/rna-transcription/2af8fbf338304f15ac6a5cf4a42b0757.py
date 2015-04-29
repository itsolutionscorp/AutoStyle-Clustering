# -*- coding: utf-8 -*-


class DNA(object):
    """DNA strand."""
    def __init__(self, strand_str):
        self._strand_str = strand_str

    def to_rna(self):
        """Tranform DNA strand to RNA string."""
        return self._strand_str.replace('T', 'U')
