def word_count(phrase):
    words = phrase.split()
    return dict([[word, words.count(word)] for word in set(words)])
