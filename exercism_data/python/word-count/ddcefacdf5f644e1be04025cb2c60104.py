def word_count(words):
    words = words.split()
    unique = set(words)
    counts = []
    for w in unique:
        counts.append(words.count(w))
    return dict(zip(unique, counts))


    
    
