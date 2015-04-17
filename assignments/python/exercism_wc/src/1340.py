def word_count(sentence):
    words = sentence.split()
    return {word: words.count(word) for word in words}
