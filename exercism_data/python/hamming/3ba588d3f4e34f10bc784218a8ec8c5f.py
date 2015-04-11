"""
The Hamming distance is defined as the number of elements in a sequence
for which the value at the analogous index is different.

e.g. 'ABCD'        'ACGA'
     'ABCE'        'CGGC'
         ^          ^^ ^
"""

def distance(seq1, seq2):
    """
    Receives two sequences
    Returns the distance between the sequences.
    """
    try:
        # if the lengths are different then return None
        if not len(seq1) == len(seq2):
            return None
        else:
            hamm = 0
            length = len(seq1)          # This just for clarity.

            for i in range(length):
                if seq1[i] != seq2[i]:
                    hamm += 1

            return hamm
    except TypeError:
        # At least one of the params is not a sequence.
        # Hamming distance can't be computed.
        return None
