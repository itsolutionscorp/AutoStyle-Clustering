def hamming(strand1, strand2):
    difference = 0;
    if len(strand1) > len(strand2):
        difference = len(strand1) - len(strand2);
        strand1 = strand1[0:len(strand2)];
    if len(strand2) > len(strand1):
        difference = len(strand2) - len(strand1);
        strand2 = strand2[0:len(strand1)];
    for i in range(len(strand1)):
        if strand1[i] != strand2[i]:
            difference += 1;
    return difference;
