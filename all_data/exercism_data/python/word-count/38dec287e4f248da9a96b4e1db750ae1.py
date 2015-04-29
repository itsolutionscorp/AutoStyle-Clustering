def word_count(phrase):
    words = {}
    for word in phrase.split():
        if words.has_key(word):
            words[word] += 1
        else:
            words[word] = 1
    return words
