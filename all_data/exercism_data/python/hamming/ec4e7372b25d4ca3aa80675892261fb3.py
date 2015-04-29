def hamming(strand1, strand2):
    if strand1 == '' or strand1 == strand2:
        return 0
    len1 = len(strand1)
    len2 = len(strand2)
    min_len = min(len1, len2)
    diff_len = abs(len1 - len2)
    diff_sum =  sum([strand1[i] != strand2[i] for i in range(min_len)])
    return diff_sum + diff_len
