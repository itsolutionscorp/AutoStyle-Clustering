class DNA(object):

    """DNA strand"""

    def __init__(self, sequence):
        """Create a new DNA strand from a string."""
        self.sequence = sequence.upper()

    def hamming_distance(self, other_sequence):
        """Return hamming-distance to another sequence."""
        return sum([n1 != n2 for n1, n2 in zip(self.sequence, other_sequence)])
