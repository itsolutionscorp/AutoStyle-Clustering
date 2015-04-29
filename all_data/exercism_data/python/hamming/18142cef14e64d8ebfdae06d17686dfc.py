def hamming(strand1, strand2):
    count = 0
    short = 0   #shortest length
    difference = 0  #difference in length
    if len(strand1) <= len(strand2):
        short = len(strand1)
        difference = len(strand2) - len(strand1)
    else: 
        short = len(strand2)
        difference = len(strand1) - len(strand2)
    for x in range(0, short):
        if strand1[x] != strand2[x]:
            count += 1
    return count + difference
