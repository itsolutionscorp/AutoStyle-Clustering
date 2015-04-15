def word_count(string):
    counts = {}
    for word in string.split():
        counts[word] = counts.get(word,0) + 1
    
    return counts
