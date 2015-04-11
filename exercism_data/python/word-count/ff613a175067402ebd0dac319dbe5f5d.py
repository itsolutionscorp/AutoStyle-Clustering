def word_count(words):
    words = words.split()
    counts = {}
    for w in words:
        counts[w] = counts[w] + 1 if w in counts else 1
    return counts


    
    
