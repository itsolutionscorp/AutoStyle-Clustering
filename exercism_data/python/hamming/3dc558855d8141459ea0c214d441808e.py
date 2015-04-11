def distance(strand1, strand2):
    count = 0
    char = 0
    while char < len(strand1):
        if strand1[char] != strand2[char]:
            count += 1
        char += 1
    return count
