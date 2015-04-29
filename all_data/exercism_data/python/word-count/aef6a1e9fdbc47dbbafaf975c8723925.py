def word_count(sentence):
    counts = dict()
    for word in sentence.split():
        counts[word] = counts.get(word,0)+1
    return counts
