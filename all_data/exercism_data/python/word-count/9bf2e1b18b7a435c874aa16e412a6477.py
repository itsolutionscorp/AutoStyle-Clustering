"""
returns the number of words in a given phrase
"""
def word_count(phrase):
    # initialize words counter
    words = {}

    # count each word
    for word in phrase.split():
        if word in words:
            words[word] += 1
        else:
            words[word] = 1
    return words
