''' Implementation of dna.py which transcribes
    DNA sequences to RNA sequences.

.. module:: dna.py
    :platforms: All
    :synopis: Transcribes DNA sequences to RNA sequences

.. moduleauthor:: Keith Gray <idahogray@gmail.com>

'''


class DNA(object):
    def __init__(self, dna_sequence):
        ''' Initialization for the DNA object

            :param sequence: The DNA sequence that needs to be transcribed
            :type sequence: str

        '''
        self.dna_sequence = dna_sequence

    def to_rna(self):
        ''' Transcribe the NDA sequence to RNA and return it

            :returns: RNA sequence transcribed from the DNA sequence provided
                      during object construction
            :rtype: str

        '''
        rna_sequence = []
        for dna_nucleotide in self.dna_sequence:
            rna_nucleotide = {
                'C': self._transcribe_c,
                'G': self._transcribe_g,
                'T': self._transcribe_t,
                'A': self._transcribe_a,
            }[dna_nucleotide]()
            rna_sequence.append(rna_nucleotide)

        return ''.join(rna_sequence)

    def _transcribe_c(self):
        ''' Transcribes C in the DNA sequence to G in the RNA sequence

            :returns: The character G
            :rtype: str

        '''
        return 'G'

    def _transcribe_g(self):
        ''' Transcribes G in the DNA sequence to C in the RNA sequence

            :returns: The character C
            :rtype: str

        '''
        return 'C'

    def _transcribe_t(self):
        ''' Transcribes T in the DNA sequence to A in the RNA sequence

            :returns: The character A
            :rtype: str

        '''
        return 'A'

    def _transcribe_a(self):
        ''' Transcribes A in the DNA sequence to U in the RNA sequence

            :returns: The character U
            :rtype: str

        '''
        return 'U'
