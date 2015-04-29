def word_count(phrase):
    counts = dict()
    words = phrase.split()
    for word in words:
        if word in counts:
            counts[word] += 1
        else:
            counts[word] = 1
    return counts
            
    
