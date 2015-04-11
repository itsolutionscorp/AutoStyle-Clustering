def word_count(sentence):
    words = sentence.split()
    occurrences = {}
    for w in words:
        occurrences[w] = occurrences.get(w, 0) + 1
    return occurrences
