def hamming(sequence1, sequence2):
    
    # We can save time if the two sequences are equal.
    if sequence1==sequence2: return 0
    
    # A couple of variables:
    hamming_distance = 0
    length_difference = abs(len(sequence1) - len(sequence2))
    
    # Walk the nucleotide sequences up to the length of the shortest one,
    # counting the differences.
    for nucleotide in range(min(len(sequence1), len(sequence2))):
        if sequence1[nucleotide] != sequence2[nucleotide]:
            # We've spotted a difference. Increment the Hamming distance!
            hamming_distance += 1

    # To the Hamming distance, add the length difference between the sequences.
    hamming_distance += length_difference
    
    # Return the Hamming distance.
    return hamming_distance
