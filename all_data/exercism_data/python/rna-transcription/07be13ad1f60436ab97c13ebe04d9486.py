# -*- coding: utf-8 -*-
import string


class DNA(object):
    """DNA strand."""
    _RNA_TRANFORMATION_TABLE = string.maketrans('T', 'U')

    def __init__(self, strand_str):
        self._strand_str = strand_str

    def to_rna(self):
        """Tranform DNA strand to RNA string."""
        return self._strand_str.translate(
            self.__class__._RNA_TRANFORMATION_TABLE
        )
