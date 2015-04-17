def word_count(sentence):
    return {word: sentence.count(word) for word in set(sentence.split())}
