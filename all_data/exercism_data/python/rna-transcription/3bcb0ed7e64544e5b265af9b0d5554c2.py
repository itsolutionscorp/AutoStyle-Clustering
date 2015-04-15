class DNA:
    
    """DNA strand"""
    
    def __init__(self, sequence):
        """Create a new DNA strand from a string."""
        self.sequence = sequence.upper()
    
    def to_rna(self):
        """Return transcribed RNA strand."""
        return self.sequence.replace('T', 'U')
