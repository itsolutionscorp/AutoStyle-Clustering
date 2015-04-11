def word_count(sentence):
    words = sentence.split()
    unique_words = set(words)
    return dict((word, words.count(word)) for word in unique_words)
