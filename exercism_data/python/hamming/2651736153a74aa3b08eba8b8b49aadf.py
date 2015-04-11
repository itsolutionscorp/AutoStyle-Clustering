def hamming(sequence1, sequence2):
    
    # Strategy:
    # Make sure the sequences are the same length. If one sequence is shorter
    # than the other, pad it out so both sequences are the same length.
    # Step through the sequences, comparing the nucleotides at each index
    # position. If the nucleotides differ, increment the Hamming distance.
    # --------------------------------------------------------------------------
    
    # Determine whether we need to pad one or the other sequence so they have
    # the same length. We pad with hyphens because we want to make sure the 
    # padding counts as a difference.
    if len(sequence1) > len(sequence2):
        # Sequence 1 is longer; Sequence 2 needs padding.
        for pad in range(0,len(sequence1) - len(sequence2)):
            sequence2 = sequence2 + '-'
    
    if len(sequence1) < len(sequence2):
        # Sequence 1 is shorter and needs padding.
        for pad in range(0,len(sequence2) - len(sequence1)):
            sequence1 = sequence1 + '-'
    
    # We'll store our count here.
    hamming_distance = 0

    # Walk the nucleotide sequences, counting up the differences.
    for nucleotide in range(0,len(sequence1)):
        if sequence1[nucleotide] != sequence2[nucleotide]:
            # We've spotted a difference. Better increment the Hamming distance!
            hamming_distance += 1
    
    # Return the Hamming distance.
    return hamming_distance
