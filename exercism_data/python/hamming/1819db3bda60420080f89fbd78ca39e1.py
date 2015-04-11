def hamming(dna1, dna2):
    # Ensure the strings are of the same length
    # If not, add placeholder characters to the shorter one
    difference = len(dna2) - len(dna1)
    if difference < 0:
        dna2 = dna2 + "x" * abs(difference)
    if difference > 0:
        dna1 = dna1 + "x" * difference

    # Count characters that are different
    hamming_distance = 0
    for i in range(len(dna1)):
        if dna1[i] != dna2[i]:
            hamming_distance += 1
    return(hamming_distance)
