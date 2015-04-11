"""rna-transcription exercise"""

class DNA(object):
    """A string of DNA."""
    def __init__(self, nucls):
        self._nucls = nucls

    def to_rna(self):
        """Convert the DNA string to RNA."""
        return self._nucls.replace('T', 'U')
