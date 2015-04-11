def distance(seq_1, seq_2):
    """
    Input:  2 DNA sequences as strings
    Output: Hamming distance(the number of differences 
            between two strings) as integer
    The strings must be of the same length
    """
    assert(len(seq_1) == len(seq_2))

    sequences = zip(seq_1,seq_2)
    hamming = 0

    for letter_pair in sequences:
        if letter_pair[0] != letter_pair[1]:
            hamming += 1

    return hamming
        
