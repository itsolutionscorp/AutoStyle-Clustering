class DNA:
    """ Returns the counts of symbols in a string"""
    symbols = ['A', 'C', 'G', 'T'] 

    def __init__(self, dnaString):
        self.dnaString = dnaString

    def count(self, n):
        if n not in DNA.symbols and n != 'U':
            msg = '{} is not a nucleotide.'.format(n)
            raise ValueError(msg)
        return self.dnaString.count(n)

    def nucleotide_counts(self):
        result = {}
        for symbol in DNA.symbols:
           result[symbol] = self.count(symbol) 
        return result
