__author__ = 'jos'


class DNA():
    def __init__(self, dnastring):
        """
        Creates a DNA object with the passed in dna string.
        :param dnastring: string
        """
        assert isinstance(dnastring, basestring)
        self.dnastring = dnastring

    def to_rna(self):
        """
        Converts dnastring to an rna string
        :return: rna string
        """
        return self.dnastring.replace('T', 'U')
