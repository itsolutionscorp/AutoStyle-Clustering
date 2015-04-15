def hamming(word1, word2):
    total = 0
    
    for a,b in zip(word1, word2):
        if a!=b:
            total += 1

    total += abs(len(word1) - len(word2))
    return total
