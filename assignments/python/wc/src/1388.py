def word_count(phrase):
    words = phrase.split()
    word_count = {word:words.count(word) for word in words}
    return word_count
