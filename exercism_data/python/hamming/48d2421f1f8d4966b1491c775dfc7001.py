def hamming(strand1,strand2):
    """
    (str,str)-> int

    returns number of nucleotide mutations between two strands of DNA
    """

    return abs(len(strand1) - len(strand2)) + sum(
        (strand1[i] != strand2[i]) for i in range(min(len(strand1), len(strand2))))
