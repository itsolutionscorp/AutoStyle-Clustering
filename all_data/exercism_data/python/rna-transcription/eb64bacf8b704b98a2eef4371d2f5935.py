class DNA:
    translation = {'A': 'U', 'T': 'A', 'C':'G', 'G': 'C'}
    
    def __init__(self, sequence):
        self.seq = sequence
        
    def to_rna(self):
        return ''.join(self.translation[letter] for letter in self.seq)
