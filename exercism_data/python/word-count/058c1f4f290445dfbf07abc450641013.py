def word_count(words):
    counts = {}
    for w in words.split():
        counts[w] = counts.get(w,0) + 1
    return counts


    
    
