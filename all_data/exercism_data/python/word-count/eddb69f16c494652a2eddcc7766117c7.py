from collections import Counter
def word_count(words):
    counter = Counter()
    for word in words.split():
        alphanum_word = ''.join(c for c in word if c.isalnum())
        if alphanum_word:
            counter[alphanum_word.lower()] += 1
    return counter
