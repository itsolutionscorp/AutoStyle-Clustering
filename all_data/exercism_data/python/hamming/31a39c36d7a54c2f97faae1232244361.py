def distance(strand1, strand2):
    if len(strand1) != len(strand2):
        return "Undefined; Strands must be of the same length"

    # Iterate through the nucleotides and count the number of differences
    differences = 0
    for index in range(len(strand1)):
        if strand1[index] != strand2[index]:
            differences += 1
    return differences
