from collections import Counter
def word_count(phrase):
    words = phrase.split()  # Split the given phrase by whitespace
    return Counter(words)
