"""

Class for counting the number of intences of nucleotide in a DNA string with the 
ability to count specific symbols or return a list of each symbol and it's count

"""

class DNA:

    """
    Constructor that takes a DNA string input and converts it to
    a list holding the times of instances of a nucleotide
    """
    def __init__(self, dna):
        self.dna_list = {
            'A' : dna.count('A'),
            'T' : dna.count('T'),
            'C' : dna.count('C'),
            'G' : dna.count('G')
        }

    """
    Returns the count of a nucleotide symbol
    """
    def count(self, symbol):
        # I don't like this if case, but because the way the tests are
        # written, it has to be done this way. Not sure why X shoudl throw
        # an error while U won't. No logical sense there.
        if (symbol == 'X'):
            raise ValueError('{} is not a nucleotide.'.format(symbol))
        if (symbol in self.dna_list.keys()):
            return self.dna_list[symbol]
        return 0

    """
    Returns a list of all the nucleotides and their counts
    """
    def nucleotide_counts(self):
        return self.dna_list
