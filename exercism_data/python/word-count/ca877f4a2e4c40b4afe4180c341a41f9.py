def word_count(phrase):
    word_hash = {}
    phrase_arr = phrase.replace("\n", " ").split()
    for word in phrase_arr:
        word_hash.setdefault(word, 0)
        word_hash[word] = word_hash[word] + 1
    return word_hash
