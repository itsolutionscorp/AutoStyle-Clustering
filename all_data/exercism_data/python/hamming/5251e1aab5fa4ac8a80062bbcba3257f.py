def hamming(sequence1, sequence2):
    '''
    By counting the number of differences between two homologous DNA strands
    taken from different genomes with a common ancestor, we get a measure of
    the minimum number of point mutations that could have occurred on the
    evolutionary path between the two strands.

    This is called the 'Hamming distance'

       GAGCCTACTAACGGGAT
       CATCGTAATGACGGCCT
       ^ ^ ^  ^ ^    ^^

    The Hamming distance between these two DNA strands is 7.
    '''
    
    # Strategy:
    # Get a count of the longer of the two sequences.
    # Iterate over the sequences, comparing the nucleotides at each index
    # position. If the nucleotides differ, increment the Hamming distance.
    
    hamming_distance = 0
    
    # Determine whether we need to pad one or the other sequence so they have
    # the same length.
    if len(sequence1) > len(sequence2):
    	# 1 is longer; 2 needs padding.
    	for pad in range(0,len(sequence1) - len(sequence2)):
    		sequence2 = sequence2 + '-'
    
    if len(sequence1) < len(sequence2):
    	# 1 is shorter and needs padding.
    	for pad in range(0,len(sequence2) - len(sequence1)):
    		sequence1 = sequence1 + '-'

    sequence_length = len(sequence1)
    
    for nucleotide in range(0,sequence_length):
    	if sequence1[nucleotide] != sequence2[nucleotide]:
    		hamming_distance += 1
    
    return hamming_distance
