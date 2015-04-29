class DNA:
    def __init__(self, s):
        self.s = s

    def to_rna(self):
        return self.s.replace('T', 'U')

execfile('rna-transcription_test.py')
