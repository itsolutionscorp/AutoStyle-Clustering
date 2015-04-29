def hamming(strand1,strand2):
    if len(strand1) < len(strand2):
        shorter = strand1
        longer = strand2
    else:
        shorter = strand2
        longer = strand1
    diff = len(longer) - len(shorter)
    
    return sum(1 for pos in range(len(shorter)) if shorter[pos] != longer[pos]) + diff

    
