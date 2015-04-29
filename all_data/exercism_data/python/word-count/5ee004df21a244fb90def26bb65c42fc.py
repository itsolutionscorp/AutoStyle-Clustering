def word_count(words):
    words = words.split()
    word_counts = {}
    for word in words:
        word_counts[word] = words.count(word)
    return(word_counts)
