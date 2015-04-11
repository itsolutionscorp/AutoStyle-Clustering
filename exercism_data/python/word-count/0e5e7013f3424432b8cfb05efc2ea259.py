def word_count(text):
    counts = {}
    
    for word in text.split():
        counts[word] = counts.setdefault(word, 0) + 1
    
    return counts
