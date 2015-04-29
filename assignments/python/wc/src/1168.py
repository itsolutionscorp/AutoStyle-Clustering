def word_count(words):
    words = words.split()
    word_counts = {}
    for i in range(0, len(words)):
        word_counts[words[i]] = words.count(words[i])
    return(word_counts)
