"""exercism.io - Python 'Hamming' exercise solution."""

def hamming(strand1, strand2):
    """Returns number of differences between strand1 and strand2"""
    strand1_len = len(strand1)
    strand2_len = len(strand2)

    count = abs(strand1_len - strand2_len)

    for i in range(min((strand1_len, strand2_len))):
        if strand1[i] != strand2[i]:
            count += 1

    return count
