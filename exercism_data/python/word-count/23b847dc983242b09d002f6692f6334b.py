def word_count(phrase):
    # Use dictionary comprehension to create a dict object with
    # a key-value pair of word and count.
    words = phrase.split()
    return {word: words.count(word) for word in words}
