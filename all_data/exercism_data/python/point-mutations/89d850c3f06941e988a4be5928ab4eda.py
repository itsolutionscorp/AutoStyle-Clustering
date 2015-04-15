class DNA(object):
    """Represents a strand of DNA"""

    def __init__(self, strand_nucleotide_sequence):
        """creates DNA for provided base code
        note the strand is a string sequence of
        DNA nucleotide letter codes
        representing the coding sequence
        (not the transcription sequence)
        """
        self.strand = strand_nucleotide_sequence

    def hamming_distance(self, other_strand):
        strand_pairs = zip(self.strand, other_strand)

        def is_different((first,second)):
            return first != second

        return len(filter(is_different,strand_pairs))
