def word_count(sentence):
    words = sentence.split()
    counts = dict()
    for word in words:
        counts[word] = counts.get(word,0)+1
    return counts
