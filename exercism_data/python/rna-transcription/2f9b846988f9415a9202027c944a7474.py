# -*- coding: utf-8 -*-
import string


class DNA(object):
    """DNA strand."""
    _RNA_TRANFORMATION_TABLE = string.maketrans('T', 'U')

    def __init__(self, strand_str):
        self.strand_str = strand_str

    def to_rna(self):
        """Tranform DNA strand to RNA string."""
        return self.strand_str.translate(DNA._RNA_TRANFORMATION_TABLE)
