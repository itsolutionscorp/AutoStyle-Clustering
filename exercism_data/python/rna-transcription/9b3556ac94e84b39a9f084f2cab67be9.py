'''dna.py - a class for DNA processing'''


class DNA(object):
    '''DNA class'''

    def __init__(self, dnasequence):
        '''Initialising DNA class, storing dnasequence'''
        self.dnasequence = dnasequence

    def to_rna(self):
        '''Transfrom DNA to RNA, Replace T -> U '''
        return self.dnasequence.replace('T', 'U')
