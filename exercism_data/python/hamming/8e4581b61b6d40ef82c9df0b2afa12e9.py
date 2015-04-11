def distance(seq1, seq2):

    # init
    hamming_distance = 0
    
    if len(seq1) != len(seq2):
        return 'DNA sequences are not of equal length'
    else:
        for index in range(len(seq1)):
            if seq1.upper()[index] != seq2.upper()[index]:
                hamming_distance += 1

    return hamming_distance


# ---- main

seq1 = input('Enter 1st DNA sequence: ')
seq2 = input('Enter 2nd DNA sequence: ')

hamming = distance(seq1, seq2)

print('\nThe Hamming distance is', hamming)
