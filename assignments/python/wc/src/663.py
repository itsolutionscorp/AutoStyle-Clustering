def word_count(words):
    word_map = {}
    for word in words.split():
        word_map[word] = 1 + word_map.get(word, 0)
    return word_map
