def word_count(phrase):
    phrase_split = phrase.split()
    return {word: phrase_split.count(word) for word in phrase_split}
