def word_count(line):
    words = {}
    for w in line.split():
        words[w] = words.get(w, 0) + 1

    return words
