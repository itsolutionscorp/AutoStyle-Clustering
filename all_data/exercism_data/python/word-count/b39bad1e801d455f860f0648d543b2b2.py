def word_count(words):
    counts = dict() 
    for word in words.split():
        if word not in counts:
            counts[word] = 1 
        else:
            counts[word] += 1
    return counts
