def hamming(strand1,strand2):
    """
    (str,str)-> int

    returns number of nucleotide mutations between two strands of DNA
    """

    ham_distance = abs(len(strand1) - len(strand2))
    for i in range(len(strand1) if len(strand1) < len(strand2) else len(strand2)):
        ham_distance += strand1[i] != strand2[i]
    return ham_distance
