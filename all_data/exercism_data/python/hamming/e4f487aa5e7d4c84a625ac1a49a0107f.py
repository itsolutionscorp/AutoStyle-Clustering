def hamming(strand1,strand2):
    """
    (str,str)-> int

    returns number of nucleotide mutations between two strands of DNA
    """

    #pad the shorter strand with bogus data
    if len(strand1) > len(strand2):
        strand2 += 'X' * (len(strand1) - len(strand2))
    else:
        strand1 += 'X' * (len(strand2) - len(strand1))

    ham_distance = 0
    for i in range(len(strand1)):
        ham_distance += strand1[i] != strand2[i]
    return ham_distance
