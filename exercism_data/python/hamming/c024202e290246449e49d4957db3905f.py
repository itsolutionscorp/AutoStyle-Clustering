def hamming(s1, s2):
    count = 0
    if not s1 and not s2:
        return 0
    if len(s1) > len(s2):
        longer = s1
        shorter = s2
    else:
        longer = s2
        shorter = s1
    diff = len(longer) - len(shorter)
    return sum(ch1 != ch2 for ch1, ch2 in zip(longer[:len(longer) - diff],
                                              shorter)) + diff



print hamming('AAGCTAC','ACGTTACGTC')
print hamming('A','G')
