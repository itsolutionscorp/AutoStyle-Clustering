def word_count(string):
    counts = {}
    for word in string.split():
        if word in counts:
            counts[word] += 1
        else:
            counts[word] = 1
    return counts

    
