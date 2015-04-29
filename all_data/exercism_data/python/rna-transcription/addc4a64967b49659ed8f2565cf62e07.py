class DNA(object):
    """A class for representing a DNA sequence."""
    def __init__(self, dna):
        self.dna = dna
        
        # Strings are immutable, so we need to use a list for
        # replacing all occurences of one character with another.
        rna_list = list(dna)

        # Replace all occurences of 'T' with 'U'.
        for (i, char) in enumerate(rna_list):
            if char == 'T':
                rna_list[i] = 'U'

        # Turn the rna sequence back into a string.
        self.rna = ''.join(rna_list)

    def to_rna(self):
        return self.rna
